package com.spring.ecmmvc.model;

import jakarta.persistence.*;

/**
 * Category represents a product category in the system.
 * It is mapped to the database as an entity with a unique ID and a name.
 */
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer categoryId;

    private String name;

    /**
     * Gets the ID of the category.
     *
     * @return The ID of the category.
     */
    public Integer getId() {
        return categoryId;
    }

    /**
     * Sets the ID of the category.
     *
     * @param id The ID to set for the category.
     */
    public void setId(Integer id) {
        this.categoryId = id;
    }

    /**
     * Gets the name of the category.
     *
     * @return The name of the category.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category.
     *
     * @param name The name to set for the category.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a string representation of the category.
     *
     * @return A string representation of the category.
     */
    @Override
    public String toString() {
        return "Category{" +
                "id=" + categoryId +
                ", name='" + name + '\'' +
                '}';
    }
}