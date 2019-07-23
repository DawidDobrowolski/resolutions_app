package pl.resolutions.controller;

import com.google.gson.Gson;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.resolutions.entity.Resolution;
import pl.resolutions.entity.UserResolution;
import pl.resolutions.service.ResolutionService;
import pl.resolutions.support.ResolutionDashboardChart;
import pl.resolutions.support.UnitsName;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/resolution")
public class ResolutionController {


    private ResolutionService resolutionService;

    @Autowired
    public ResolutionController(ResolutionService resolutionService) {
        this.resolutionService = resolutionService;
    }

    @ModelAttribute("resolutions")
    public List<Resolution> getAllResolutions() {
        return resolutionService.getAllResolutions();
    }


    @ModelAttribute("userResolutions")
    public List<UserResolution> getAllResolutions(HttpServletRequest request) {
        return resolutionService.getAllUserResolutions(request);
    }


    @GetMapping("/dashboard")
    public String dashboardPage(Model model, HttpServletRequest request) {
        List<ResolutionDashboardChart> dashboardCharts = resolutionService.getDashboardChart(request);
        Gson gson = new Gson();
        model.addAttribute("dashboardCharts", gson.toJson(dashboardCharts));
        return "/resolution/dashboard";
    }

    @GetMapping("/add")
    public String addResolution(Model model) {
        List<UnitsName> unitsNames = resolutionService.getUnits();
        Gson gson = new Gson();
        model.addAttribute("unitsNames", gson.toJson(unitsNames));
        model.addAttribute("userResolution", new UserResolution());
        return "/resolution/add";
    }

    @PostMapping("/add")
    public String saveForm(Model model, @Valid UserResolution userResolution, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            List<UnitsName> unitsNames = resolutionService.getUnits();
            Gson gson = new Gson();
            model.addAttribute("unitsNames", gson.toJson(unitsNames));
            return "resolution/add";
        }
        resolutionService.addUserResolution(request, userResolution);
        return "redirect:/resolution/dashboard";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        UserResolution userResolution = resolutionService.getOneUserResolution(id);
        List<UnitsName> unitsNames = resolutionService.getUnits();

        Gson gson = new Gson();
        model.addAttribute("unitsNames", gson.toJson(unitsNames));
        model.addAttribute("userResolution", userResolution);
        return "resolution/add";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        resolutionService.deleteUserResolution(id);
        return "redirect:/resolution/dashboard";
    }


    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable Long id) {
        UserResolution userResolution = resolutionService.getOneUserResolution(id);
        model.addAttribute("userResolution", userResolution);
        return "resolution/details";
    }


}
