package com.spring.ecmmvc.controller;

import com.spring.ecmmvc.service.CartService;
import com.spring.ecmmvc.service.CategoryService;
import com.spring.ecmmvc.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ShopController handles shop-related operations, such as displaying products,
 * filtering products by category, and viewing individual product details.
 */
@Controller
public class ShopController {

    private final ProductService productService;
    private final CategoryService categoryService;

    /**
     * Constructor for ShopController.
     *
     * @param productService  Service for managing product-related operations.
     * @param categoryService Service for managing category-related operations.
     */
    public ShopController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;

        this.categoryService = categoryService;
    }

    /**
     * Displays the shop page with all products and categories.
     *
     * @param model The model to add attributes for the view.
     * @return The view name for the shop page.
     */
    @GetMapping("/shop")
    public String shopPage(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "shop";
    }

    /**
     * Displays products filtered by a specific category.
     *
     * @param id    The ID of the category to filter products by.
     * @param model The model to add attributes for the view.
     * @return The view name for the shop page with filtered products.
     */
    @GetMapping("/shop/category/{id}")
    public String productByCategory(@PathVariable int id, Model model) {
        model.addAttribute("products", productService.getProductByCategoryId(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "shop";
    }

    /**
     * Displays detailed information about a specific product.
     *
     * @param id    The ID of the product to view.
     * @param model The model to add attributes for the view.
     * @return The view name for the product details page.
     */
    @GetMapping("shop/viewproduct/{id}")
    public String viewProduct(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "viewProduct";
    }
}