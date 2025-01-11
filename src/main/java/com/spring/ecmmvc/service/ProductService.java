package com.spring.ecmmvc.service;

import com.spring.ecmmvc.dao.ProductDao;
import com.spring.ecmmvc.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProductService provides business logic for managing products, including saving,
 * retrieving, updating, and deleting products.
 */
@Service
public class ProductService {

    private final ProductDao productDao;
    private final CartProductService cartProductService;

    /**
     * Constructor for ProductService.
     *
     * @param productDao         The DAO for accessing product data.
     * @param cartProductService The service for managing cart-product relationships.
     */
    public ProductService(ProductDao productDao, CartProductService cartProductService) {
        this.productDao = productDao;
        this.cartProductService = cartProductService;
    }

    /**
     * Saves a new product or updates an existing one.
     *
     * @param product The product to save or update.
     * @return The saved or updated product.
     */
    public Product saveProduct(Product product) {
        return productDao.save(product);
    }

    /**
     * Retrieves all products.
     *
     * @return A list of all products.
     */
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    /**
     * Deletes a product by its ID, including removing it from all carts.
     *
     * @param id The ID of the product to delete.
     */
    @Transactional
    public void deleteProductById(int id) {
        cartProductService.deleteProductFromCartById(id);
        productDao.deleteById(id);
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id The ID of the product to retrieve.
     * @return The product with the specified ID.
     * @throws RuntimeException If the product is not found.
     */
    public Product getProductById(int id) {
        return productDao.findById(id).orElseThrow(() -> new RuntimeException("Product Not found By Id " + id));
    }

    /**
     * Retrieves products by their associated category ID.
     *
     * @param categoryId The ID of the category to filter products by.
     * @return A list of products associated with the specified category ID.
     */
    public List<Product> getProductByCategoryId(int categoryId) {
        return productDao.findProductByCategory_Id(categoryId);
    }

    /**
     * Saves or updates the image name for a product.
     *
     * @param product   The product to update.
     * @param imageName The name of the image file.
     * @return The updated product.
     */
    public Product saveImageForProduct(Product product, String imageName) {
        product.setImage(imageName);
        return productDao.save(product);
    }
}