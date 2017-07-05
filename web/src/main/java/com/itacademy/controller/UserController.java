package com.itacademy.controller;

import com.itacademy.entity.Category;
import com.itacademy.entity.EnumFlashmobType;
import com.itacademy.entity.Event;
import com.itacademy.entity.SystemUser;
import com.itacademy.service.CategoryService;
import com.itacademy.service.FlashmobService;
import com.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Arrays;
import java.util.List;

import static com.itacademy.entity.QUser.user;

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
    public SystemUser user() {
        return new SystemUser();
    }

    @ModelAttribute("allFlashmobTypes")
    public List<EnumFlashmobType> enumFlashmob() {
        return Arrays.asList(EnumFlashmobType.values());
    }

    @ModelAttribute("allEvents")
    public List<Event> allEvents() {
        return flashmobService.findAllEvents();
    }

    @ModelAttribute("allCategories")
    public List<Category> categories() {
        return categoryService.findAll();
    }



    @GetMapping("/login")
    public String showLoginPage() {
        return "login-form";
    }

    @GetMapping("/home")
    public String showHomePage() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        System.out.println(username);
        return "main-page-user";
    }

    @GetMapping(path = "/registration")
    public String showRegistrationForm() {
        return "registration-form";
    }

    @PostMapping(path = "/registration")
    public String saveUser(SystemUser user, @RequestParam String name,
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
        return "main-page-user";
    }
}
