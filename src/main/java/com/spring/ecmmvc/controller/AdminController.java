package com.spring.ecmmvc.controller;

import com.spring.ecmmvc.model.Category;
import com.spring.ecmmvc.model.Product;
import com.spring.ecmmvc.service.CategoryService;
import com.spring.ecmmvc.service.FileService;
import com.spring.ecmmvc.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * AdminController is responsible for handling admin-related operations,
 * including managing categories and products.
 */
@Controller
public class AdminController {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final FileService fileService;

    /**
     * Constructor for AdminController.
     *
     * @param categoryService Service for managing categories.
     * @param productService  Service for managing products.
     * @param fileService     Service for handling file uploads.
     */
    public AdminController(CategoryService categoryService, ProductService productService, FileService fileService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.fileService = fileService;
    }

    /**
     * Displays the admin home page.
     *
     * @return The view name for the admin home page.
     */
    @GetMapping("/admin")
    public String adminHome() {
        return "adminHome";
    }

    /**
     * Displays a list of all categories.
     *
     * @param model The model to add attributes for the view.
     * @return The view name for the categories page.
     */
    @GetMapping("/admin/categories")
    public String adminCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories";
    }

    /**
     * Displays the form for adding a new category.
     *
     * @param model The model to add attributes for the view.
     * @return The view name for the category add form.
     */
    @GetMapping("/admin/categories/add")
    public String adminCategoriesAdd(Model model) {
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }

    /**
     * Handles the submission of the category add form.
     *
     * @param category The category object to be saved.
     * @return Redirects to the categories list page.
     */
    @PostMapping("/admin/categories/add")
    public String adminCategoriesAdd(@ModelAttribute("category") Category category) {
        categoryService.saveCategory(category);
        return "redirect:/admin/categories";
    }

    /**
     * Deletes a category by its ID.
     *
     * @param id The ID of the category to delete.
     * @return Redirects to the categories list page.
     */
    @GetMapping("/admin/categories/delete/{id}")
    public String adminCategoriesDelete(@PathVariable int id) {
        categoryService.deleteCategoryById(id);
        return "redirect:/admin/categories";
    }

    /**
     * Displays the form for updating an existing category.
     *
     * @param id    The ID of the category to update.
     * @param model The model to add attributes for the view.
     * @return The view name for the category update form.
     */
    @GetMapping("/admin/categories/update/{id}")
    public String adminCategoriesUpdate(@PathVariable int id, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "categoriesUpdate";
    }

    /**
     * Handles the submission of the category update form.
     *
     * @param category The updated category object.
     * @return Redirects to the categories list page.
     */
    @PostMapping("/admin/categories/sumbitUpdate")
    public String adminCategoriesSumbitUpdate(@ModelAttribute("category") Category category) {
        System.out.println(category);
        categoryService.updateCategory(category);
        return "redirect:/admin/categories";
    }

    /**
     * Displays a list of all products.
     *
     * @param model The model to add attributes for the view.
     * @return The view name for the products page.
     */
    @GetMapping("/admin/products")
    public String adminProduct(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    /**
     * Displays the form for adding a new product.
     *
     * @param model The model to add attributes for the view.
     * @return The view name for the product add form.
     */
    @GetMapping("/admin/products/add")
    public String adminProductAdd(Model model) {
        model.addAttribute("productDTO", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "productsAdd";
    }

    /**
     * Handles the submission of the product add form.
     *
     * @param product The product object to be saved.
     * @param file    The image file associated with the product.
     * @return Redirects to the products list page.
     */
    @PostMapping("/admin/products/add")
    public String adminProductAdd(@ModelAttribute("productDTO") Product product,
                                  @RequestParam("productImage") MultipartFile file) {
        fileService.saveFileToResources(file);
        productService.saveProduct(product);
        productService.saveImageForProduct(product, file.getName());
        return "redirect:/admin/products";
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id The ID of the product to delete.
     * @return Redirects to the products list page.
     */
    @GetMapping("/admin/product/delete/{id}")
    public String adminProductDelete(@PathVariable int id) {
        productService.deleteProductById(id);
        return "redirect:/admin/products";
    }

    /**
     * Displays the form for updating an existing product.
     *
     * @param id    The ID of the product to update.
     * @param model The model to add attributes for the view.
     * @param file  The image file associated with the product.
     * @return The view name for the product update form.
     */
    @GetMapping("/admin/product/update/{id}")
    public String adminProductUpdate(@PathVariable int id, Model model,
                                     @RequestParam("productImage") MultipartFile file) {
        model.addAttribute("productDTO", productService.getProductById(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        fileService.saveFileToResources(file);
        productService.saveImageForProduct(productService.getProductById(id), file.getOriginalFilename());
        return "productsUpdate";
    }

    /**
     * Handles the submission of the product update form.
     *
     * @param product The updated product object.
     * @return Redirects to the products list page.
     */
    @PostMapping("admin/products/submitUpdate")
    public String adminProductSubmitUpdate(@ModelAttribute("productDTO") Product product) {
        productService.saveProduct(product);
        return "redirect:/admin/products";
    }
}