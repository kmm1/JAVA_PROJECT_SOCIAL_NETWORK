package com.itacademy.controller;


import com.itacademy.entity.EnumGender;
import com.itacademy.entity.EnumMaritalStatus;
import com.itacademy.entity.Profile;
import com.itacademy.entity.User;
import com.itacademy.service.ProfileService;
import com.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
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

    @ModelAttribute("profile")
    public Profile profile() {
        return new Profile();
    }

    @ModelAttribute("allEnumGender")
    public List<EnumGender> enumGenders() {
        return Arrays.asList(EnumGender.values());
    }

    @ModelAttribute("allEnumMaritalStatus")
    public List<EnumMaritalStatus> enumMaritalStatus() {
        return Arrays.asList(EnumMaritalStatus.values());
    }


    @GetMapping(path = "/profile")
    public String showMyProfile(Model model, HttpServletRequest req) {
        Long userId = (Long) req.getSession().getAttribute("userId");
        String userName = (String) req.getSession().getAttribute("userName");
        List<Profile> profile2 = profileService.findProfileByUserId(userId);
        if (profile2.isEmpty()) {
            return "profile-form-save";
        } else
            model.addAttribute("profile", profile2);
        model.addAttribute("userName", userName);
        return "profile";
    }

    @GetMapping(path = "/addProfile")
    public String profileAdd( Model model, HttpServletRequest req) {
        Long userId = (Long) req.getSession().getAttribute("userId");
        Profile profile = profileService.findOneProfileByUserId(userId);
        model.addAttribute("profile", profile);
        return "profile-form-update";
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

    //TODO ne soxran enbedded
    @PostMapping(path = "/updateProfile")
    public String profileUpdate(Model model, HttpServletRequest req,
                                @RequestParam String aboutMe,
                                @RequestParam String maritalStatus,
                                @RequestParam String gender) {
        Long userId = (Long) req.getSession().getAttribute("userId");
        String userName = (String) req.getSession().getAttribute("userName");
        List<Profile> profile2 = profileService.findProfileByUserId(userId);
        Profile profile = profile2.get(0);
        profile.setAboutMe(aboutMe);
        profile.setMaritalStatus(EnumMaritalStatus.valueOf(maritalStatus));
        profile.setGender(EnumGender.valueOf(gender));
        profileService.update(profile);
        List<Profile> profile3 = profileService.findProfileByUserId(userId);
        model.addAttribute("profile", profile3);
        model.addAttribute("userName", userName);
//        System.out.println("JJJJJJJJJJJJJJJJ");
//        System.out.println(dayOfBirth);
        return "profile";
    }


}
