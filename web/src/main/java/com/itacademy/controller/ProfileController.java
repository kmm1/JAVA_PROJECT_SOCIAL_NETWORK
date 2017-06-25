package com.itacademy.controller;


import com.itacademy.entity.Profile;
import com.itacademy.entity.User;
import com.itacademy.service.ProfileService;
import com.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {

    private final ProfileService profileService;
    private final UserService userService;


    @Autowired
    public ProfileController(ProfileService profileService, UserService userService) {
        this.profileService = profileService;
        this.userService = userService;
    }


    @GetMapping(path = "/profile")
    public String showMyProfile(Model model, HttpServletRequest req) {
        Long userId = (Long) req.getSession().getAttribute("userId");
        String userName = (String) req.getSession().getAttribute("userName");
        List<Profile> profile = profileService.findProfileByUserId(userId);
        model.addAttribute("profile", profile);
        model.addAttribute("userName", userName);
        if (profile.isEmpty()) {
            return "profile-form-save";
        } else return "profile";
    }

    @GetMapping(path = "/addProfile")
    public String profileAdd() {
        return "profile-form-save";
    }

    @PostMapping(path = "/saveProfile")
    public String profileSave(Profile profile, Model model, HttpServletRequest req) {
        Long userId = (Long) req.getSession().getAttribute("userId");
        String userName = (String) req.getSession().getAttribute("userName");
        User user = userService.findById(userId);
        profile.setUser(user);
        Long id = profileService.save(profile);
        System.out.println(profile);
        List<Profile> profile2 = profileService.findProfileByUserId(userId);
        model.addAttribute("profile", profile2);
        model.addAttribute("userName", userName);
        return "profile";
    }

//    @PostMapping(path = "/updateProfile")
//    public String profileUpdate(Profile profile, Model model, HttpServletRequest req) {
//        Long userId = (Long) req.getSession().getAttribute("userId");
//        String userName = (String) req.getSession().getAttribute("userName");
//        List<Profile> profile = profileService.findProfileByUserId(userId);
//      //  profileService.update(profile.get(0));
//        System.out.println(profile.get(0));
//        System.out.println(profile);
//
////        List<Profile> profile2 = profileService.findProfileByUserId(userId);
////        model.addAttribute("profile", profile2);
////        model.addAttribute("userName", userName);
//        return "profile-form-update";
//    }


}
