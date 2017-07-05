package com.itacademy.controller;

import com.itacademy.entity.Friend;
import com.itacademy.entity.SystemUser;
import com.itacademy.service.FriendService;
import com.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
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

    @ModelAttribute("roles")
    public Collection<? extends GrantedAuthority> roles() {
        Collection<? extends GrantedAuthority> roles = SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities();
        return roles;
    }

    @ModelAttribute("friend")
    public Friend friend() {
        return new Friend();
    }


    @GetMapping(path = "/friend")
    public String showMyFriends(Friend friend, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = ((UserDetails) principal).getUsername();
        ArrayList<Friend> findAllFriendsByUserName = (ArrayList<Friend>) friendService.findAllFriendsByUserName(userName);
        List<String> myFriends1 = new ArrayList<>();
        for (int i = 0; i < findAllFriendsByUserName.size(); i++) {
            myFriends1.add(findAllFriendsByUserName.get(i).getUserReceiver().getName());
            myFriends1.add(findAllFriendsByUserName.get(i).getUserSender().getName());
        }
        while (myFriends1.contains(userName)) {
            myFriends1.remove(userName);
        }

        ArrayList<Friend> findAllMyFriendRequestsSent = (ArrayList<Friend>) friendService.findAllMyFriendRequestsSent(userName);
        List<String> myFriends2 = new ArrayList<>();
        for (int i = 0; i < findAllMyFriendRequestsSent.size(); i++) {
            myFriends2.add(findAllMyFriendRequestsSent.get(i).getUserReceiver().getName());
        }


        ArrayList<Friend> findAllMyFriendRequestsResived = (ArrayList<Friend>) friendService.findAllMyFriendRequestsResived(userName);
        List<String> myFriends3 = new ArrayList<>();
        for (int i = 0; i < findAllMyFriendRequestsResived.size(); i++) {
            myFriends3.add(findAllMyFriendRequestsResived.get(i).getUserSender().getName());
        }

        ArrayList<SystemUser> allUsers = (ArrayList<SystemUser>) userService.findAll();
        List<String> users = new ArrayList<>();
        for (int i = 0; i < allUsers.size(); i++) {
            users.add(allUsers.get(i).getName());
        }
        while (users.contains(userName)) {
            users.remove(userName);
        }
        for (int i = 0; i < myFriends1.size(); i++) {
            while (users.contains(myFriends1.get(i))) {
                users.remove(myFriends1.get(i));
            }
        }
        for (int i = 0; i < myFriends2.size(); i++) {
            while (users.contains(myFriends2.get(i))) {
                users.remove(myFriends2.get(i));
            }
        }
        for (int i = 0; i < myFriends3.size(); i++) {
            while (users.contains(myFriends3.get(i))) {
                users.remove(myFriends3.get(i));
            }
        }
        System.out.println("JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ");
        System.out.println(users);
        model.addAttribute("myFriends1", myFriends1);
        model.addAttribute("myFriends2", myFriends2);
        model.addAttribute("myFriends3", myFriends3);
        model.addAttribute("users", users);
        return "friend";
    }

    @PostMapping(path = "/sendFriendRequest")
    public String sendFriendRequest(Model model,
                                    @RequestParam String userReceiver) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = ((UserDetails) principal).getUsername();
        SystemUser sender = userService.findOneUserByName(userName);
        SystemUser reciver = userService.findOneUserByName(userReceiver);

        Friend friend = new Friend("req", sender, reciver);
        friendService.save(friend);
        System.out.println("JJJJJJJJJJJJJJJJJJ");
        System.out.println(sender);
        System.out.println(reciver);
        return "redirect:/friend";
    }

    @PostMapping(path = "/acceptFriendRequest")
    public String acceptFriendRequest(Model model,
                                      @RequestParam String userReceiver) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = ((UserDetails) principal).getUsername();
        Friend friend = friendService.findOneFriendByUsersNames(userReceiver, userName);
        friend.setStatus("fri");
        friendService.update(friend);
        System.out.println("JJJJJJJJJJJJJJJJJJ");
        System.out.println(friend);
        return "redirect:/friend";
    }

    @PostMapping(path = "/deliteFriend")
    public String deliteFriend(Model model,
                               @RequestParam String userReceiver) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = ((UserDetails) principal).getUsername();
        Friend friend = friendService.findOneFriendByUsersNames2(userReceiver, userName);
        friendService.delete(friend);
        System.out.println("JJJJJJJJJJJJJJJJJJ");
        System.out.println(friend);
        return "redirect:/friend";
    }

}
