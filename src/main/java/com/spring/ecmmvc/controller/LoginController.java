package com.spring.ecmmvc.controller;

import com.spring.ecmmvc.model.User;
import com.spring.ecmmvc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * LoginController handles user authentication-related operations, such as displaying
 * the login page, registration page, and processing user registration.
 */
@Controller
public class LoginController {

    private final UserService userService;

    /**
     * Constructor for LoginController.
     *
     * @param userService Service for managing user-related operations.
     */
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Displays the login page.
     *
     * @return The view name for the login page.
     */
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    /**
     * Displays the registration page.
     *
     * @param model The model to add attributes for the view.
     * @return The view name for the registration page.
     */
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    /**
     * Processes user registration.
     *
     * @param user The user object containing registration details.
     * @return Redirects to the login page after successful registration.
     */
    @PostMapping("/register")
    public String processRegister(@ModelAttribute User user) {
        userService.registerUser(user);
        return "redirect:/login";
    }

    /**
     * Handles user logout by redirecting to the login page.
     *
     * @return Redirects to the login page.
     */
    @GetMapping("/logout")
    public String showLogoutPage() {
        return "redirect:/login";
    }
}