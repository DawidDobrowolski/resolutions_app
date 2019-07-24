package pl.resolutions.controller;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.resolutions.entity.Activity;
import pl.resolutions.entity.UserResolution;
import pl.resolutions.service.ActivityService;
import pl.resolutions.support.ActivityDashboardChart;
import pl.resolutions.support.UnitsName;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/activity")
public class ActivityController {

    private ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @ModelAttribute("userResolutions")
    public List<UserResolution> getAllResolutions(HttpServletRequest request) {
        return activityService.getAllActiveUserResolutionsByUser(request);
    }


    @ModelAttribute("activities")
    public List<Activity> getAllActivities(HttpServletRequest request) {
        return activityService.getAllActivities(request);
    }

    @GetMapping("/dashboard")
    public String dashboardPage(Model model, HttpServletRequest request) {
        List<ActivityDashboardChart> activityDashboardChartArrayList = activityService.getActivityDashboardChart(request);
        Gson gson = new Gson();
        model.addAttribute("dashboardCharts", gson.toJson(activityDashboardChartArrayList));
        return "/activity/dashboard";
    }


    @GetMapping("/add")
    public String addActivity(Model model, HttpServletRequest request) {
        List<UnitsName> unitsNames = activityService.getUnitsNames(request);

        Gson gson = new Gson();
        model.addAttribute("unitsNames", gson.toJson(unitsNames));
        model.addAttribute("activity", new Activity());
        return "/activity/add";
    }

    @GetMapping("/add/{id}")
    public String addActivityToUserResolution(Model model, @PathVariable Long id, HttpServletRequest request) {
        Activity activity = new Activity();
        UserResolution userResolution = activityService.getUserResolutionById(id);
        if(!userResolution.isActive()){
            return "redirect:/activity/dashboard";
        }
        activity.setUserResolution(userResolution);
        List<UnitsName> unitsNames = activityService.getUnitsNames(request);
        Gson gson = new Gson();
        model.addAttribute("unitsNames", gson.toJson(unitsNames));
        model.addAttribute("activity", activity);
        return "/activity/add";
    }


    @PostMapping("/add")
    public String saveForm(Model model, @Valid Activity activity, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {

            List<UnitsName> unitsNames = activityService.getUnitsNames(request);
            Gson gson = new Gson();
            model.addAttribute("unitsNames", gson.toJson(unitsNames));
            return "activity/add";
        }
        activityService.saveActivity(activity);
        return "redirect:/activity/dashboard";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id, HttpServletRequest request) {
        Activity activity = activityService.getActivityById(id);

        List<UnitsName> unitsNames = activityService.getUnitsNames(request);
        Gson gson = new Gson();
        model.addAttribute("unitsNames", gson.toJson(unitsNames));
        model.addAttribute("activity", activity);
        return "activity/add";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        activityService.deleteActivity(id);
        return "redirect:/activity/dashboard";
    }

    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable Long id) {
        Activity activity = activityService.getActivityById(id);
        model.addAttribute("activity", activity);
        return "activity/details";
    }

}
