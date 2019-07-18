package pl.resolutions.controller;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.resolutions.entity.Activity;
import pl.resolutions.entity.Resolution;
import pl.resolutions.entity.User;
import pl.resolutions.entity.UserResolution;
import pl.resolutions.repository.ActivityRepository;
import pl.resolutions.repository.ResolutionRepository;
import pl.resolutions.repository.UserRepository;
import pl.resolutions.repository.UserResolutionRepository;
import pl.resolutions.support.ActivityDashboardChart;
import pl.resolutions.support.UnitsName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/activity")
public class ActivityController {

    private ActivityRepository activityRepository;
    private UserResolutionRepository userResolutionRepository;
    private ResolutionRepository resolutionRepository;
    private UserRepository userRepository;


    @Autowired
    public ActivityController(ActivityRepository activityRepository, UserResolutionRepository userResolutionRepository,ResolutionRepository resolutionRepository,UserRepository userRepository) {
        this.activityRepository = activityRepository;
        this.userResolutionRepository = userResolutionRepository;
        this.resolutionRepository = resolutionRepository;
        this.userRepository = userRepository;
    }


    @ModelAttribute("userResolutions")
    public List<UserResolution> getAllResolutions(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return userResolutionRepository.getByUserEmailAndActiveIsTrue((String)session.getAttribute("email"));
    }

    @ModelAttribute("activities")
    public List<Activity> getAllActivities(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<UserResolution> userResolutions = userResolutionRepository.getByUserEmail((String)session.getAttribute("email"));
        List<Activity> activities = new ArrayList<>();
        for (UserResolution userResolution:userResolutions){
            activities.addAll(activityRepository.getActivitiesByUserResolution(userResolution));
        }
        return activities;
    }

    @GetMapping("/dashboard")
    public String dashboardPage(Model model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = userRepository.getByEmail((String)session.getAttribute("email"));
        List<Resolution> resolutionList = resolutionRepository.customDistinctUserResolutionsByUserEmail(user.getEmail());
        List<ActivityDashboardChart> activityDashboardChartArrayList = new ArrayList<>();

        for(Resolution resolution : resolutionList){
            ActivityDashboardChart activityDashboardChart = new ActivityDashboardChart();
            activityDashboardChart.setResolution(resolution.getName());
            activityDashboardChart.setNumber(activityRepository.countActivitiesByUserResolutionResolutionAndUserResolutionUser(resolution,user));
            activityDashboardChartArrayList.add(activityDashboardChart);
        }

        Gson gson = new Gson();
        model.addAttribute("dashboardCharts", gson.toJson(activityDashboardChartArrayList));
        return "/activity/dashboard";
    }


    @GetMapping("/add")
    public String addActivity(Model model,HttpServletRequest request) {
        List<UserResolution> userResolutionList = getAllResolutions(request);
        List<UnitsName> unitsNames = new ArrayList<>();
        for(UserResolution userResolution:userResolutionList){
            UnitsName unitsName = new UnitsName();
            unitsName.setId(userResolution.getId());
            unitsName.setName(userResolution.getResolution().getUnit());
            unitsNames.add(unitsName);
        }
        Gson gson = new Gson();
        model.addAttribute("unitsNames", gson.toJson(unitsNames));


        model.addAttribute("activity", new Activity());
        return "/activity/add";
    }

    @GetMapping("/add/{id}")
    public String addActivityToUserResolution(Model model, @PathVariable Long id,HttpServletRequest request) {
        UserResolution userResolution = userResolutionRepository.findOne(id);
        Activity activity = new Activity();
        activity.setUserResolution(userResolution);

        List<UserResolution> userResolutionList = getAllResolutions(request);
        List<UnitsName> unitsNames = new ArrayList<>();
        for(UserResolution userResolution2:userResolutionList){
            UnitsName unitsName = new UnitsName();
            unitsName.setId(userResolution2.getId());
            unitsName.setName(userResolution2.getResolution().getUnit());
            unitsNames.add(unitsName);
        }
        Gson gson = new Gson();
        model.addAttribute("unitsNames", gson.toJson(unitsNames));;
        model.addAttribute("activity", activity);
        return "/activity/add";
    }


    @PostMapping("/add")
    public String saveForm(Model model,@Valid Activity activity, BindingResult result,HttpServletRequest request) {
        if (result.hasErrors()) {

            List<UserResolution> userResolutionList = getAllResolutions(request);
            List<UnitsName> unitsNames = new ArrayList<>();
            for(UserResolution userResolution:userResolutionList){
                UnitsName unitsName = new UnitsName();
                unitsName.setId(userResolution.getId());
                unitsName.setName(userResolution.getResolution().getUnit());
                unitsNames.add(unitsName);
            }
            Gson gson = new Gson();
            model.addAttribute("unitsNames", gson.toJson(unitsNames));



            return "activity/add";
        }
        activityRepository.save(activity);
        return "redirect:/activity/dashboard";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model,@PathVariable Long id,HttpServletRequest request) {
        Activity activity = activityRepository.findOne(id);

        List<UserResolution> userResolutionList = getAllResolutions(request);
        List<UnitsName> unitsNames = new ArrayList<>();
        for(UserResolution userResolution:userResolutionList){
            UnitsName unitsName = new UnitsName();
            unitsName.setId(userResolution.getId());
            unitsName.setName(userResolution.getResolution().getUnit());
            unitsNames.add(unitsName);
        }
        Gson gson = new Gson();
        model.addAttribute("unitsNames", gson.toJson(unitsNames));
        model.addAttribute("activity", activity);
        return "activity/add";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        activityRepository.delete(id);
        return "redirect:/activity/dashboard";
    }

    @GetMapping("/details/{id}")
    public String details(Model model,@PathVariable Long id) {
        Activity activity =  activityRepository.findOne(id);
        model.addAttribute("activity",activity);
        return "activity/details";
    }

}
