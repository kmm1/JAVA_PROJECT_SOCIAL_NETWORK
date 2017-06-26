package com.itacademy.controller;

import com.itacademy.entity.Friend;
import com.itacademy.service.FriendService;
import com.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class FriendController {


    private final FriendService friendService;
    private final UserService userService;


    @Autowired
    public FriendController(FriendService friendService, UserService userService) {
        this.friendService = friendService;
        this.userService = userService;
    }

    @GetMapping(path = "/friend")
    public String showMyFriends(Model model, HttpServletRequest req) {
        Long userId = (Long) req.getSession().getAttribute("userId");
        String userName = (String) req.getSession().getAttribute("userName");
        List<Friend> findAllFriendsByUserName = friendService.findAllFriendsByUserName(
                userName);
        List<Friend> findAllMyFriendRequestsResived = friendService.findAllMyFriendRequestsResived(
                userName);
        List<Friend> findAllMyFriendRequestsSent = friendService.findAllMyFriendRequestsSent(
                userName);
        model.addAttribute("findAllFriendsByUserName", findAllFriendsByUserName);
        model.addAttribute("findAllMyFriendRequestsResived", findAllMyFriendRequestsResived);
        model.addAttribute("findAllMyFriendRequestsSent", findAllMyFriendRequestsSent);
        return "friend";
    }

    @PostMapping(path = "/sendFriendRequest")
    public String sendFriendRequest(Model model, HttpServletRequest req) {
        String senderName = (String) req.getSession().getAttribute("userName");
String s = "vova";
      //  friendService.sendFriendRequest(senderName, s);
        return "redirect:/friend";
    }

    @PostMapping(path = "/acceptFriendRequest")
    public String acceptFriendRequest(Model model, HttpServletRequest req) {
        String senderName = (String) req.getSession().getAttribute("userName");
        return "redirect:/friend";
    }


}
