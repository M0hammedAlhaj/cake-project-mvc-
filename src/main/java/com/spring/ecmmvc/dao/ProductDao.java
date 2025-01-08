package com.spring.ecmmvc.dao;

import com.spring.ecmmvc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * ProductDao is a Spring Data JPA repository interface for managing CRUD operations
 * on the {@link Product} entity. It extends {@link JpaRepository} to inherit standard
 * methods for interacting with the database.
 */
public interface ProductDao extends JpaRepository<Product, Integer> {

    /**
     * Finds products by their associated category ID.
     *
     * @param categoryId The ID of the category to filter products by.
     * @return A list of products associated with the specified category ID.
     */
    List<Product> findProductByCategory_Id(int categoryId);
}