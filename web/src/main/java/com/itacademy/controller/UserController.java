package com.itacademy.controller;

import com.itacademy.entity.*;
import com.itacademy.service.CategoryService;
import com.itacademy.service.FlashmobService;
import com.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SessionAttributes(names = {"userName", "userId"})
@Controller
public class UserController {

    private final UserService userService;
    private final FlashmobService flashmobService;
    CategoryService categoryService;


    @Autowired
    public UserController(UserService userService, FlashmobService flashmobService, CategoryService categoryService) {
        this.userService = userService;
        this.flashmobService = flashmobService;
        this.categoryService = categoryService;


    }

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @ModelAttribute("allFlashmobTypes")
    public List<EnumFlashmobType> enumFlashmob() {
        return Arrays.asList(EnumFlashmobType.values());
    }

    @ModelAttribute("allCategories")
    public List<Category> categories() {
        return categoryService.findAll();
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
            List<Event> allEvents = flashmobService.findAllEvents();
            model.addAttribute("allEvents", allEvents);
            model.put("userName", user.get(0).getName());
            model.put("userId", user.get(0).getId());
            return "main-page-user";
        } else if (user.get(0).getRole().equals("admin")) {
            List<Event> allEvents = flashmobService.findAllEvents();
            model.addAttribute("allEvents", allEvents);
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
    public String saveUser(User user, @RequestParam String name,
                           @RequestParam String password, @RequestParam String email,
                           ModelMap model) {
        if (name.equals("") || password.equals("") || email.equals("")) {
            return "registration-form";
        }
        userService.save(user);
        return "login-form";
    }

    @GetMapping(path = "/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "login-form";
    }

    @GetMapping(path = "/mainPageUser")
    public String mainPageUser(Model model) {
        List<Event> allEvents = flashmobService.findAllEvents();
        model.addAttribute("allEvents", allEvents);
        return "main-page-user";
    }
}
