package com.spring.ecmmvc.controller;

import com.spring.ecmmvc.model.User;
import com.spring.ecmmvc.service.CartService;
import com.spring.ecmmvc.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * CartController handles all cart-related operations, such as displaying the cart,
 * adding items to the cart, and removing items from the cart.
 */
@Controller
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    /**
     * Constructor for CartController.
     *
     * @param cartService Service for managing cart operations.
     * @param userService Service for managing user-related operations.
     */
    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    /**
     * Displays the cart page for the authenticated user.
     *
     * @param model       The model to add attributes for the view.
     * @param userDetails The authenticated user's details.
     * @return The view name for the cart page.
     */
    @GetMapping("/cart")
    public String cartPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findUserByEmail(userDetails.getUsername());
        model.addAttribute("cart", cartService.getCartById(user.getCart().getIdCart()));
        model.addAttribute("price", cartService.calculatePriceOfItemById(user.getCart().getIdCart()));
        return "cart";
    }

    /**
     * Adds a product to the authenticated user's cart.
     *
     * @param id          The ID of the product to add to the cart.
     * @param model       The model to add attributes for the view.
     * @param userDetails The authenticated user's details.
     * @return Redirects to the shop page.
     */
    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findUserByEmail(userDetails.getUsername());
        cartService.addProductByIdToCart(id, user.getCart().getIdCart());
        return "redirect:/shop";
    }

    /**
     * Removes an item from the authenticated user's cart.
     *
     * @param index       The index of the item to remove from the cart.
     * @param userDetails The authenticated user's details.
     * @return Redirects to the cart page.
     */
    @GetMapping("/cart/removeItem/{index}")
    public String removeItem(@PathVariable int index, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findUserByEmail(userDetails.getUsername());
        cartService.removeProductFromCartById(index, user.getCart().getIdCart());
        return "redirect:/cart";
    }
}