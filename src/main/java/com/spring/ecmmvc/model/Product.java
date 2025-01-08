package com.spring.ecmmvc.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Product represents a product entity in the system.
 * It is mapped to the database as an entity with attributes such as name, category, price, weight, description, and image.
 */
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer productId;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    private Category category;

    private BigDecimal price;

    private BigDecimal weight;

    private String description;

    private String image;

    /**
     * Gets the ID of the product.
     *
     * @return The ID of the product.
     */
    public Integer getId() {
        return productId;
    }

    /**
     * Sets the ID of the product.
     *
     * @param id The ID to set for the product.
     */
    public void setId(Integer id) {
        this.productId = id;
    }

    /**
     * Gets the name of the product.
     *
     * @return The name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     *
     * @param name The name to set for the product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the category of the product.
     *
     * @return The category of the product.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets the category of the product.
     *
     * @param category The category to set for the product.
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Gets the price of the product.
     *
     * @return The price of the product.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price The price to set for the product.
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets the weight of the product.
     *
     * @return The weight of the product.
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the product.
     *
     * @param weight The weight to set for the product.
     */
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    /**
     * Gets the description of the product.
     *
     * @return The description of the product.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the product.
     *
     * @param description The description to set for the product.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the image of the product.
     *
     * @return The image of the product.
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the image of the product.
     *
     * @param image The image to set for the product.
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Checks if this product is equal to another object.
     *
     * @param o The object to compare with.
     * @return True if the objects are equal, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId);
    }

    /**
     * Returns the hash code for the product.
     *
     * @return The hash code for the product.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(productId);
    }
}