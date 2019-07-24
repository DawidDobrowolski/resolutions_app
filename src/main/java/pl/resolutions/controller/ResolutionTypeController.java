package pl.resolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.resolutions.entity.Resolution;
import pl.resolutions.service.ResolutionTypeService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/resolutionType")
public class ResolutionTypeController {


    private ResolutionTypeService resolutionTypeService;

    @Autowired
    public ResolutionTypeController(ResolutionTypeService resolutionTypeService) {
        this.resolutionTypeService = resolutionTypeService;
    }

    @ModelAttribute("resolutionTypes")
    public List<Resolution> getAllResolutionTypes() {
        return resolutionTypeService.getAllResolutionTypes();
    }


    @GetMapping("/list")
    public String resolutionTypeList() {
        return "/resolutionType/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        resolutionTypeService.deleteResolutionType(id);
        return "redirect:/resolutionType/list";
    }

    @GetMapping("/edit/{id}")
    public String editResolutionType(Model model, @PathVariable Long id) {
        Resolution resolution = resolutionTypeService.getResolutionById(id);
        model.addAttribute("resolution", resolution);
        return "resolutionType/add";
    }

    @PostMapping("/add")
    public String saveResolutionType(@Valid Resolution resolution, BindingResult result) {
        if (result.hasErrors()) {
            return "resolutionType/add";
        }

       resolutionTypeService.saveResolutionType(resolution);
        return "redirect:/resolutionType/list";
    }

    @GetMapping("/add")
    public String addResolutionType(Model model) {
        model.addAttribute("resolution", new Resolution());
        return "resolutionType/add";
    }

}
