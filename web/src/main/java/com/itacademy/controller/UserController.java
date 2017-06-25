package com.itacademy.controller;

import com.itacademy.entity.User;
import com.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SessionAttributes(names = {"userName", "userId"})
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

        List<User> user = userService.findUserByNamePassword(name, password);
        model.put("userName", user.get(0).getName());
        model.put("userId", user.get(0).getId());
        if (user.isEmpty()) {
            return "login-form";
        } else if (user.get(0).getRole().equals("user")) {
            return "main-page-user";
        } else return "main-page-admin";
    }


    @GetMapping(path = "/registration")
    public String showRegistrationForm() {
        return "registration-form";
    }

    @PostMapping(path = "/registration")
    public String saveUser(User user) {
        user.setRole("user");
        userService.save(user);
        return "login-form";
    }

    @GetMapping(path = "/logout")
    public String logout() {
        return "login-form";
    }

}
