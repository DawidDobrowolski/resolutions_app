package pl.resolutions.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.resolutions.entity.User;
import pl.resolutions.service.UserService;
import pl.resolutions.validation.EditUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

private UserService userService;

@Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @ModelAttribute("users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping("/list")
    public String dashboardPage() {
         return "/user/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/user/list";
    }


    @GetMapping("/edit/{id}")
    public String editUser(Model model, @PathVariable Long id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user/edit";
    }

    @PostMapping("/edit")
    public String saveEditUser(@Validated({EditUser.class}) User user, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "user/edit";
        }

        User editedUser = userService.getUserById(user.getId());

        if (userService.getUserByEmail(user.getEmail()) != null) {
            if (userService.getUserByEmail(user.getEmail()).getId() != editedUser.getId()) {
                result.addError(new FieldError("user", "email", "user already exist"));
                return "user/edit";
            }

        }

        editedUser.setFirstName(user.getFirstName());
        editedUser.setLastName(user.getLastName());
        editedUser.setEmail(user.getEmail());
        editedUser.setAdmin(user.isAdmin());
        userService.saveUser(editedUser);


        HttpSession session = request.getSession();
        if(session.getAttribute("email").equals(editedUser.getEmail())){
            session.setAttribute("email", user.getEmail());
            session.setAttribute("firstName", user.getFirstName());
        }


        return "redirect:/user/list";
    }

}
