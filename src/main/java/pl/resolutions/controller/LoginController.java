package pl.resolutions.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.resolutions.entity.User;
import pl.resolutions.service.LoginService;
import pl.resolutions.validation.EditPassword;
import pl.resolutions.validation.EditUser;

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
            result.addError(new FieldError("user", "password", "passwords do not match"));
            return "login/register";
        }

        if (loginService.getUserByEmail(user.getEmail()) != null) {
            result.addError(new FieldError("user", "email", "user already exist"));
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
        session.setAttribute("admin", existingUser.isAdmin());
        return "redirect:/resolution/dashboard";
    }


    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String editUser(Model model, HttpServletRequest request) {
        User user = loginService.getUserBySession(request);
        model.addAttribute("user", user);
        return "login/edit";
    }

    @PostMapping("/edit")
    public String saveEditUser(@Validated({EditUser.class}) User user, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "login/edit";
        }

        User loggedUser = loginService.getUserBySession(request);

        if (loginService.getUserByEmail(user.getEmail()) != null) {
            if (loginService.getUserByEmail(user.getEmail()).getId() != loggedUser.getId()) {
                result.addError(new FieldError("user", "email", "user already exist"));
                return "login/edit";
            }

        }

        HttpSession session = request.getSession();
        session.setAttribute("email", user.getEmail());
        session.setAttribute("firstName", user.getFirstName());

        loggedUser.setFirstName(user.getFirstName());
        loggedUser.setLastName(user.getLastName());
        loggedUser.setEmail(user.getEmail());

        loginService.saveUser(loggedUser);
        return "redirect:/resolution/dashboard";
    }

    @GetMapping("/changePassword")
    public String changePassword(Model model) {
        model.addAttribute("user", new User());
        return "login/changePassword";
    }

    @PostMapping("/changePassword")
    public String saveChangePassword(Model model, @Validated({EditPassword.class}) User user, BindingResult result, HttpServletRequest request, @RequestParam String currentPassword, @RequestParam String confirmPassword) {
        User loggedUser = loginService.getUserBySession(request);

        if (!BCrypt.checkpw(currentPassword, loggedUser.getPassword())) {
            model.addAttribute("currentFalse", true);
            return "login/changePassword";
        }


        if (result.hasErrors()) {
            return "login/changePassword";
        }

        if (!user.getPassword().equals(confirmPassword)) {
            result.addError(new FieldError("user", "password", "passwords do not match"));
            return "login/changePassword";
        }


        loggedUser.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

        loginService.saveUser(loggedUser);
        return "redirect:/resolution/dashboard";
    }

}
