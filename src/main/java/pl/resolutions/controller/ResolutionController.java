package pl.resolutions.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.resolutions.entity.Resolution;
import pl.resolutions.entity.UserResolution;
import pl.resolutions.repository.ResolutionRepository;
import pl.resolutions.repository.UserRepository;
import pl.resolutions.repository.UserResolutionRepository;
import pl.resolutions.support.ResolutionDashboardChart;
import pl.resolutions.support.UnitsName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/resolution")
public class ResolutionController {

    private UserResolutionRepository userResolutionRepository;
    private ResolutionRepository resolutionRepository;
    private UserRepository userRepository;


    @Autowired
    public ResolutionController(UserResolutionRepository userResolutionRepository, ResolutionRepository resolutionRepository, UserRepository userRepository) {
        this.userResolutionRepository = userResolutionRepository;
        this.resolutionRepository = resolutionRepository;
        this.userRepository = userRepository;
    }

    @ModelAttribute("resolutions")
    public List<Resolution> getAllResolutions() {
        return resolutionRepository.findAll();
    }


    @ModelAttribute("userResolutions")
    public List<UserResolution> getAllResolutions(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return userResolutionRepository.getByUserEmail((String) session.getAttribute("email"));
    }


    @GetMapping("/dashboard")
    public String dashboardPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<UserResolution> userResolutions = userResolutionRepository.getByUserEmail((String) session.getAttribute("email"));
        List<ResolutionDashboardChart> dashboardCharts = new ArrayList<>();
        for (UserResolution userResolution : userResolutions) {
            ResolutionDashboardChart resolutionDashboardChart = new ResolutionDashboardChart();
            resolutionDashboardChart.setName(userResolution.getName());
            resolutionDashboardChart.setDone(userResolution.getLastActivitiesUnits());
            if (userResolution.getWeeklyPlan() > userResolution.getLastActivitiesUnits()) {
                resolutionDashboardChart.setToGo(userResolution.getWeeklyPlan() - userResolution.getLastActivitiesUnits());
            } else {
                resolutionDashboardChart.setToGo(0);
            }
            dashboardCharts.add(resolutionDashboardChart);
        }
        Gson gson = new Gson();
        model.addAttribute("dashboardCharts", gson.toJson(dashboardCharts));
        return "/resolution/dashboard";
    }

    @GetMapping("/add")
    public String addResolution(Model model) {
        List<Resolution> resolutionList = getAllResolutions();
        List<UnitsName> unitsNames = new ArrayList<>();
        for(Resolution resolution:resolutionList){
            UnitsName unitsName = new UnitsName();
            unitsName.setId(resolution.getId());
            unitsName.setName(resolution.getUnit());
            unitsNames.add(unitsName);
        }
        Gson gson = new Gson();
        model.addAttribute("unitsNames", gson.toJson(unitsNames));
        model.addAttribute("userResolution", new UserResolution());
        return "/resolution/add";
    }

    @PostMapping("/add")
    public String saveForm(Model model,@Valid UserResolution userResolution, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {

            List<Resolution> resolutionList = getAllResolutions();
            List<UnitsName> unitsNames = new ArrayList<>();
            for(Resolution resolution:resolutionList){
                UnitsName unitsName = new UnitsName();
                unitsName.setId(resolution.getId());
                unitsName.setName(resolution.getUnit());
                unitsNames.add(unitsName);
            }
            Gson gson = new Gson();
            model.addAttribute("unitsNames", gson.toJson(unitsNames));

            return "resolution/add";
        }
        HttpSession session = request.getSession();
        userResolution.setUser(userRepository.getByEmail((String) session.getAttribute("email")));
        userResolutionRepository.save(userResolution);
        return "redirect:/resolution/dashboard";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        UserResolution userResolution = userResolutionRepository.findOne(id);
        List<Resolution> resolutionList = getAllResolutions();
        List<UnitsName> unitsNames = new ArrayList<>();
        for(Resolution resolution:resolutionList){
            UnitsName unitsName = new UnitsName();
            unitsName.setId(resolution.getId());
            unitsName.setName(resolution.getUnit());
            unitsNames.add(unitsName);
        }
        Gson gson = new Gson();
        model.addAttribute("unitsNames", gson.toJson(unitsNames));
        model.addAttribute("userResolution", userResolution);
        return "resolution/add";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userResolutionRepository.delete(id);
        return "redirect:/resolution/dashboard";
    }


    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable Long id) {
        UserResolution userResolution = userResolutionRepository.findOne(id);
        model.addAttribute("userResolution", userResolution);
        return "resolution/details";
    }


}
