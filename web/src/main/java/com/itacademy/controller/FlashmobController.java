package com.itacademy.controller;

import com.itacademy.entity.*;
import com.itacademy.service.BlogService;
import com.itacademy.service.FlashmobService;
import com.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
    public String updateEvent(@PathVariable("eventId") Long eventId, Model model) {
        Flashmob myFlashmob = flashmobService.findById(eventId);
        //TODO metod ne realizovan
        List<Event> allEvents = flashmobService.findAllEvents();
        model.addAttribute("allEvents", allEvents);
        return "main-page-admin";
    }

    @GetMapping(path = "/findEvent/{eventId}")
    public String findEvent(@PathVariable("eventId") Long eventId, Model model) {
        System.out.println(eventId);
        Flashmob myFlashmob = flashmobService.findById(eventId);
        model.addAttribute("myFlashmob", myFlashmob);
        return "flashmob-read";
    }


}
