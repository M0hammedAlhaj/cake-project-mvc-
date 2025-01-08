package com.spring.ecmmvc.service;

import com.spring.ecmmvc.dao.CategoryDao;
import com.spring.ecmmvc.model.Category;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CategoryService provides business logic for managing categories, including saving,
 * retrieving, updating, and deleting categories.
 */
@Service
public class CategoryService {

    private final CategoryDao categoryDao;

    /**
     * Constructor for CategoryService.
     *
     * @param categoryDao The DAO for accessing category data.
     */
    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    /**
     * Saves a new category or updates an existing one.
     *
     * @param category The category to save or update.
     * @return The saved or updated category.
     */
    public Category saveCategory(Category category) {
        return categoryDao.save(category);
    }

    /**
     * Retrieves all categories.
     *
     * @return A list of all categories.
     */
    public List<Category> getAllCategories() {
        return categoryDao.findAll();
    }

    /**
     * Deletes a category by its ID.
     *
     * @param id The ID of the category to delete.
     */
    public void deleteCategoryById(int id) {
        categoryDao.deleteById(id);
    }

    /**
     * Retrieves a category by its ID.
     *
     * @param id The ID of the category to retrieve.
     * @return The category with the specified ID.
     * @throws EntityNotFoundException If the category is not found.
     */
    public Category getCategoryById(int id) {
        return categoryDao.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Updates an existing category.
     *
     * @param category The category to update.
     * @return The updated category.
     * @throws EntityNotFoundException If the category is not found.
     */
    public Category updateCategory(Category category) {
        if (categoryDao.findById(category.getId()).isEmpty()) {
            throw new EntityNotFoundException("Category not found");
        }

        return categoryDao.save(category);
    }
}