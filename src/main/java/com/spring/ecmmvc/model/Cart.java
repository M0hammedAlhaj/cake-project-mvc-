package com.spring.ecmmvc.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Cart represents a user's shopping cart, which contains a list of products.
 * It is mapped to the database as an entity with a many-to-many relationship
 * to the {@link Product} entity.
 */
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idCart;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "cart_product", // Join table name
            joinColumns = @JoinColumn(name = "id_cart"), // Foreign key to Cart
            inverseJoinColumns = @JoinColumn(name = "id_product") // Foreign key to Product
    )
    private List<Product> products = new ArrayList<>();

    /**
     * Gets the ID of the cart.
     *
     * @return The ID of the cart.
     */
    public Integer getIdCart() {
        return idCart;
    }

    /**
     * Sets the ID of the cart.
     *
     * @param idCart The ID to set for the cart.
     */
    public void setIdCart(Integer idCart) {
        this.idCart = idCart;
    }

    /**
     * Gets the list of products in the cart.
     *
     * @return The list of products in the cart.
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Sets the list of products in the cart.
     *
     * @param products The list of products to set in the cart.
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * Returns a string representation of the cart.
     *
     * @return A string representation of the cart.
     */
    @Override
    public String toString() {
        return "Cart{" +
                "idCart=" + idCart +
                ", products=" + products +
                '}';
    }

    /**
     * Checks if this cart is equal to another object.
     *
     * @param o The object to compare with.
     * @return True if the objects are equal, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(idCart, cart.idCart);
    }

    /**
     * Returns the hash code for the cart.
     *
     * @return The hash code for the cart.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(idCart);
    }
}