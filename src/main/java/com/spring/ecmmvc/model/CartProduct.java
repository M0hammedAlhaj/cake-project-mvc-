package com.spring.ecmmvc.model;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * CartProduct represents the relationship between a cart and a product,
 * including the quantity of the product in the cart. It is mapped to the
 * database as an entity with a composite primary key using {@link CartProductId}.
 */
@Entity
@Table(name = "cart_product")
@IdClass(CartProductId.class)
public class CartProduct implements Serializable {

    @Id
    @Column(name = "id_cart")
    private int cartId;

    @Id
    @Column(name = "id_product")
    private int productId;

    private int quantity;

    /**
     * Gets the quantity of the product in the cart.
     *
     * @return The quantity of the product.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product in the cart.
     *
     * @param quantity The quantity to set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the ID of the cart.
     *
     * @return The ID of the cart.
     */
    public int getCartId() {
        return cartId;
    }

    /**
     * Sets the ID of the cart.
     *
     * @param cartId The ID to set for the cart.
     */
    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    /**
     * Gets the ID of the product.
     *
     * @return The ID of the product.
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Sets the ID of the product.
     *
     * @param productId The ID to set for the product.
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Returns a string representation of the CartProduct.
     *
     * @return A string representation of the CartProduct.
     */
    @Override
    public String toString() {
        return "CartProduct{" +
                "cartId=" + cartId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}