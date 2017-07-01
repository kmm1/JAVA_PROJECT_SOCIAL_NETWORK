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

    @GetMapping(path = "/saveMessage/{userReceiverName}")
    public String saveMessage3(@PathVariable("userReceiverName") String userReceiverName,
                               ModelMap model, HttpServletRequest req,
                               @RequestParam String text) {
        String userName = (String) req.getSession().getAttribute("userName");
        User userSender = userService.findOneUserByName(userName);
        if (userReceiverName.equals("") || userReceiverName.equals(null)) {
            return "message-error";
        }
        System.out.println("JJJJJJJJJJJJJJJJJ");
        System.out.println(userReceiverName);
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
        model.addAttribute("name", userReceiverName);
        return "redirect:/openChat/{name}";
    }


    @GetMapping(path = "/messageInput")
    public String messageInput1(Message message, ModelMap model, HttpServletRequest req) {
        Long userId = (Long) req.getSession().getAttribute("userId");
        String userName = (String) req.getSession().getAttribute("userName");
        List<String> names = messageService.names(userId, userName);
        model.addAttribute("names", names);
        System.out.println(names);
        System.out.println(model);
        return "/message-input";
    }


    @GetMapping(path = "/openChat/{name}")
    public String messageChat1(@PathVariable("name") String name,
                               Message message, ModelMap model, HttpServletRequest req) {
        Long userId = (Long) req.getSession().getAttribute("userId");
        String userName = (String) req.getSession().getAttribute("userName");
        if (name.equals("") || name.equals(null)) {
            return "message-error";
        }
        System.out.println("JJJJJJJJJJJJJJJJJJJJJJ");
        System.out.println(name);
        List<User> oneUserByName2 = userService.findOneUserByName2(name);
        if (oneUserByName2.size() == 0) {
            return "message-error";
        }
        Long id = oneUserByName2.get(0).getId();
        List<Message> messages = messageService.chatByTwoUsers(userId, id);
        model.addAttribute("messages", messages);
        model.addAttribute("userName", userName);
        model.addAttribute("name2", name);
        System.out.println(messages);
        System.out.println(name);
        return "/message-chat";
    }

    @PostMapping(path = "/openChat")
    public String messageChat2(@RequestParam String userReceiverName,
                               ModelMap model, HttpServletRequest req) {
        Long userId = (Long) req.getSession().getAttribute("userId");
        String userName = (String) req.getSession().getAttribute("userName");
        if (userReceiverName.equals("") || userReceiverName.equals(null)) {
            System.out.println("jjj"+userReceiverName);
            return "message-error";
        }
        List<User> oneUserByName2 = userService.findOneUserByName2(userReceiverName);
        if (oneUserByName2.size() == 0) {
            System.out.println("kkk"+userReceiverName);
            return "message-error";
        }
        Long id = oneUserByName2.get(0).getId();
        List<Message> messages = messageService.chatByTwoUsers(userId, id);
        model.addAttribute("messages", messages);
        model.addAttribute("userName", userName);
        model.addAttribute("name2", userReceiverName);
        System.out.println(messages);
        System.out.println(userReceiverName);
        return "/message-chat";
    }


}