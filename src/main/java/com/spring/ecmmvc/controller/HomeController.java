package com.spring.ecmmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HomeController handles requests related to the home page of the application.
 */
@Controller
public class HomeController {

    /**
     * Displays the home page.
     *
     * @return The view name for the home page.
     */
    @GetMapping("/index")
    public String homePage() {
        return "index";
    }
}