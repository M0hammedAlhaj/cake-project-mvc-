package com.spring.ecmmvc.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * CartProductId is an embeddable class representing the composite primary key
 * for the {@link CartProduct} entity. It consists of the cart ID and product ID.
 */
@Embeddable
public class CartProductId implements Serializable {

    private int cartId;
    private int productId;

    /**
     * Default constructor.
     */
    public CartProductId() {
    }

    /**
     * Constructor to initialize the composite key.
     *
     * @param cartId    The ID of the cart.
     * @param productId The ID of the product.
     */
    public CartProductId(int cartId, int productId) {
        this.cartId = cartId;
        this.productId = productId;
    }

    /**
     * Gets the cart ID.
     *
     * @return The cart ID.
     */
    public int getCartId() {
        return cartId;
    }

    /**
     * Sets the cart ID.
     *
     * @param cartId The cart ID to set.
     */
    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    /**
     * Gets the product ID.
     *
     * @return The product ID.
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Sets the product ID.
     *
     * @param productId The product ID to set.
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Checks if this CartProductId is equal to another object.
     *
     * @param o The object to compare with.
     * @return True if the objects are equal, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartProductId that = (CartProductId) o;
        return cartId == that.cartId && productId == that.productId;
    }

    /**
     * Returns the hash code for this CartProductId.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(cartId, productId);
    }
}