package com.itacademy.controller;

import com.itacademy.entity.*;
import com.itacademy.service.BlogService;
import com.itacademy.service.MessageService;
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
import java.util.List;

@Controller
public class MessageController {

    private final UserService userService;
    private final MessageService messageService;


    @Autowired
    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @ModelAttribute("message")
    public Message message() {
        return new Message();
    }

    @GetMapping(path = "/message")
    public String message(Model model) {
        return "/message";
    }

    @GetMapping(path = "/saveMessage")
    public String saveMessage1(Model model, HttpServletRequest req) {
        return "/message-form";
    }

    @PostMapping(path = "/saveMessage")
    public String saveMessage2(ModelMap model, HttpServletRequest req,
                               @RequestParam String userReceiverName, @RequestParam String text) {
        String userName = (String) req.getSession().getAttribute("userName");
        User userSender = userService.findOneUserByName(userName);
        if (userReceiverName.equals("") || userReceiverName.equals(null)) {
            return "message-error";
        }
        List<User> user = userService.findOneUserByName2(userReceiverName);
        System.out.println(user);
        if (user.size() == 0) {
            return "message-error";
        }
        Message message = new Message();
        message.setUserSender(userSender);
        message.setUserReceiver(user.get(0));
        message.setText(text);
        message.setCreationDate(LocalDateTime.now());
        messageService.save(message);
        return "redirect:/message";
    }


    @GetMapping(path = "/messageInput")
    public String messageInput1(ModelMap model, HttpServletRequest req) {
        return "redirect:/message-input";
    }


}