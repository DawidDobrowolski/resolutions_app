package pl.resolutions.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.resolutions.entity.Activity;
import pl.resolutions.entity.UserResolution;
import pl.resolutions.repository.ActivityRepository;
import pl.resolutions.repository.UserResolutionRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/activity")
public class ActivityController {

    private ActivityRepository activityRepository;
    private UserResolutionRepository userResolutionRepository;

    @Autowired
    public ActivityController(ActivityRepository activityRepository, UserResolutionRepository userResolutionRepository) {
        this.activityRepository = activityRepository;
        this.userResolutionRepository = userResolutionRepository;
    }


    @ModelAttribute("userResolutions")
    public List<UserResolution> getAllResolutions(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return userResolutionRepository.getByUserEmail((String)session.getAttribute("email"));
    }


    @GetMapping("/add")
    public String addActivity(Model model) {
        model.addAttribute("activity", new Activity());
        return "/activity/add";
    }

    @GetMapping("/add/{id}")
    public String addActivityToUserResolution(Model model, @PathVariable Long id) {
        UserResolution userResolution = userResolutionRepository.findOne(id);
        Activity activity = new Activity();
        activity.setUserResolution(userResolution);
        model.addAttribute("activity", activity);
        return "/activity/add";
    }


    @PostMapping("/add")
    public String saveForm(@Valid Activity activity, BindingResult result) {
        if (result.hasErrors()) {
            return "activity/add";
        }
        activityRepository.save(activity);
        return "redirect:/resolution/dashboard";
    }

}
