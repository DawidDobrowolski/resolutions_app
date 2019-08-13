package pl.resolutions.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(){
        return "index";
    }

    @GetMapping("/tips")
    public String tipsPage(){
        return "tips";
    }

    @GetMapping("/top")
    public String topPage(){
        return "top";
    }

    @GetMapping("/error")
    public String showErrorPage(){
        return "error";
    }
}
