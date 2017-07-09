package com.itacademy.controller;

import com.itacademy.entity.EnumFlashmobType;
import com.itacademy.entity.Event;
import com.itacademy.entity.Flashmob;
import com.itacademy.service.FlashmobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Controller
public class FlashmobController {

    private final FlashmobService flashmobService;

    @Autowired
    public FlashmobController(FlashmobService flashmobService) {
        this.flashmobService = flashmobService;
    }

    @ModelAttribute("allFlashmobTypes")
    public List<EnumFlashmobType> enumFlashmob() {
        return Arrays.asList(EnumFlashmobType.values());
    }

    @ModelAttribute("flashmob")
    public Flashmob flashmob() {
        return new Flashmob();
    }

    @ModelAttribute("roles")
    public Collection<? extends GrantedAuthority> roles() {
        Collection<? extends GrantedAuthority> roles = SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities();
        return roles;
    }


    @PostMapping(path = "/saveFlashmob")
    public String saveFlashmob(@RequestParam String name,
                               @RequestParam String holdingDate,
                               @RequestParam EnumFlashmobType flashmobType,
                               @RequestParam String aboutEvent,
                               ModelMap model) {
        Flashmob flashmob = new Flashmob();
        flashmob.setName(name);
        flashmob.setHoldingDate(holdingDate);
        flashmob.setFlashmobType(flashmobType);
        flashmob.setAboutEvent(aboutEvent);
        flashmobService.save(flashmob);
        List<Event> allEvents = flashmobService.findAllEvents();
        model.addAttribute("allEvents", allEvents);
        return "main-page-admin";
    }

    @GetMapping(path = "/deliteEvent/{eventId}")
    public String deliteEvent(@PathVariable("eventId") Long eventId, Model model) {
        System.out.println(eventId);
        Flashmob myFlashmob = flashmobService.findById(eventId);
        System.out.println(myFlashmob);
        flashmobService.delete(myFlashmob);
        List<Event> allEvents = flashmobService.findAllEvents();
        model.addAttribute("allEvents", allEvents);
        return "main-page-admin";
    }

    @GetMapping(path = "/updateEvent/{eventId}")
    public String updateEvent1(@PathVariable("eventId") Long eventId, Model model) {
        Flashmob flashmob = flashmobService.findById(eventId);
        long version = flashmob.getVersion();
        model.addAttribute("flashmob", flashmob);
        model.addAttribute("version", version);

        return "flashmob-update";
    }

    @PostMapping(path = "/updateEvent/{eventId}")
    public String updateEvent2(@PathVariable("eventId") Long eventId, Model model,
                               @RequestParam String name,
                               @RequestParam String holdingDate,
                               @RequestParam String aboutEvent,
                               @RequestParam Long version,
                               @RequestParam EnumFlashmobType flashmobType) {
        Flashmob flashmob = flashmobService.findById(eventId);
        long version1 = flashmob.getVersion();
        System.out.println(version1);
        System.out.println(version);
        if (version1 != version) {
            return "version-error";
        }
        flashmob.setFlashmobType(flashmobType);
        flashmob.setHoldingDate(holdingDate);
        flashmob.setAboutEvent(aboutEvent);
        flashmob.setName(name);
        flashmobService.update(flashmob);
        List<Event> allEvents = flashmobService.findAllEvents();
        model.addAttribute("allEvents", allEvents);
        return "main-page-admin";
    }

    @GetMapping(path = "/flashmob")
    public String findEvent1(Model model) {
        List<Event> allEvents = flashmobService.findAllEvents();
        model.addAttribute("allEvents", allEvents);
        return "flashmob";
    }

    @GetMapping(path = "/findEvent/{eventId}")
    public String findEvent2(@PathVariable("eventId") Long eventId, Model model) {
        System.out.println(eventId);
        Flashmob myFlashmob = flashmobService.findById(eventId);
        model.addAttribute("myFlashmob", myFlashmob);
        return "flashmob-read";
    }


}
