package com.spring.ecmmvc.dao;

import com.spring.ecmmvc.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CategoryDao is a Spring Data JPA repository interface for managing CRUD operations
 * on the {@link Category} entity. It extends {@link JpaRepository} to inherit standard
 * methods for interacting with the database.
 */
@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {

    /**
     * Deletes a category by its ID.
     *
     * @param id The ID of the category to delete.
     * @return The deleted category.
     */
    Category deleteCategoryById(int id);
}