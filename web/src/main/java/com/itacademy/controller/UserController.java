package com.itacademy.controller;

import com.itacademy.entity.User;
import com.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@SessionAttributes( names = {"userName", "userId"})
@Controller
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/enter")
    public String enterSite() {
        return "login-form";
    }

    @PostMapping(path = "/enter")
    public String findUserByNamePassword(@RequestParam String name,
                                         @RequestParam String password, ModelMap model) {
        if (name.equals("") || password.equals("")) {
           return "login-form";
        }
        List<User> user = userService.findUserByNamePassword(name, password);
        if (user.isEmpty()) {
            return "login-form";
        } else if (user.get(0).getRole().equals("user")) {
            model.put("userName", user.get(0).getName());
            model.put("userId", user.get(0).getId());
            return "main-page-user";
        } else if (user.get(0).getRole().equals("admin")) {
            model.put("userName", user.get(0).getName());
            model.put("userId", user.get(0).getId());
            return "main-page-admin";
        } else return "login-form";
    }



    @GetMapping(path = "/registration")
    public String showRegistrationForm() {
        return "registration-form";
    }

    @PostMapping(path = "/registration")
    public String saveUser(User user) {
        userService.save(user);
        return "login-form";
    }

    @GetMapping(path = "/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "login-form";
    }
}
