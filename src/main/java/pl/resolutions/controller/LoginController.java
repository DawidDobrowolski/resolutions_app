package pl.resolutions.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.resolutions.entity.User;
import pl.resolutions.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {


    private LoginService loginService;


    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }




    @GetMapping("/register")
    public String displayRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "login/register";

    }

    @PostMapping("/register")
    public String saveRegistrationForm(@Valid User user, BindingResult result, @RequestParam String confirmPassword) {
        if (result.hasErrors()) {
            return "login/register";
        }

        if (!user.getPassword().equals(confirmPassword)) {
            result.addError(new FieldError("user", "password", "Passwords do not match"));
            return "login/register";
        }

        if (loginService.getUserByEmail(user.getEmail()) != null) {
            result.addError(new FieldError("user", "email", "User already exist"));
            return "login/register";
        }


        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        loginService.saveUser(user);
        return "redirect:login";
    }


    @GetMapping("/login")
    public String displayLoginForm() {
        return "login/login";
    }


    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model, HttpServletRequest request) {
        boolean success = true;
        if (email == null || email.trim().equals("") || password == null || password.trim().equals("")) {
            success = false;
        }
        User existingUser = null;
        if (success) {
            existingUser = loginService.getUserByEmail(email);
            if (existingUser == null) {
                success = false;
            } else if (!BCrypt.checkpw(password, existingUser.getPassword())) {
                success = false;
            }
        }
        if (!success) {
            model.addAttribute("success", false);
            return "login/login";
        }

        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        session.setAttribute("firstName", existingUser.getFirstName());
        return "redirect:/resolution/dashboard";
    }


    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


}
