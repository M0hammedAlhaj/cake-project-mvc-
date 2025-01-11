package com.spring.ecmmvc.controller;

import com.spring.ecmmvc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HomeController handles requests related to the home page of the application.
 */
@Controller
public class HomeController {

    UserService userService ;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Displays the home page.
     *
     * @return The view name for the home page.
     */
    @GetMapping("/index")
    public String homePage() {
        System.out.println(userService.findAllUsers());
        return "index";
    }
}