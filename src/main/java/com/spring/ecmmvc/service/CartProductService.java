package com.spring.ecmmvc.service;

import com.spring.ecmmvc.dao.CartProductDao;
import com.spring.ecmmvc.model.CartProduct;
import com.spring.ecmmvc.model.CartProductId;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * CartProductService provides business logic for managing cart-product relationships,
 * including adding, updating, and removing products from a cart.
 */
@Service
public class CartProductService {

    private final CartProductDao cartProductDao;

    /**
     * Constructor for CartProductService.
     *
     * @param cartProductDao The DAO for accessing cart-product data.
     */
    public CartProductService(CartProductDao cartProductDao) {
        this.cartProductDao = cartProductDao;
    }

    /**
     * Deletes all cart products associated with a specific product ID.
     *
     * @param productId The ID of the product to delete from carts.
     */
    public void deleteProductFromCartById(int productId) {
        cartProductDao.deleteCartProductsByProductId(productId);
    }

    /**
     * Increments the quantity of a product in the cart.
     *
     * @param cartProductId The composite key (cart ID and product ID) identifying the cart-product relationship.
     */
    public void incrementProductQuantity(CartProductId cartProductId) {
        CartProduct cartProduct = cartProductDao.findById(cartProductId)
                .orElseThrow(() -> new RuntimeException("Product not found in Cart"));
        cartProduct.setQuantity(cartProduct.getQuantity() + 1);
        cartProductDao.save(cartProduct);
    }

    /**
     * Decrements the quantity of a product in the cart or removes it if the quantity is 1.
     *
     * @param cartProductId The composite key (cart ID and product ID) identifying the cart-product relationship.
     */
    @Transactional
    public void deleteProductFromCart(CartProductId cartProductId) {
        CartProduct cartProduct = cartProductDao.findById(cartProductId)
                .orElseThrow(() -> new RuntimeException("Product not found in Cart"));
        if (cartProduct.getQuantity() > 1) {
            cartProduct.setQuantity(cartProduct.getQuantity() - 1);
        } else {
            cartProductDao.deleteById(cartProductId);
        }
    }

    /**
     * Retrieves all cart products associated with a specific cart ID.
     *
     * @param cartId The ID of the cart.
     * @return A set of cart products associated with the specified cart ID.
     */
    public Set<CartProduct> getCartProductsByCartId(int cartId) {
        return cartProductDao.getAllByCartId(cartId);
    }

    /**
     * Checks if a product exists in a cart.
     *
     * @param cartProductId The composite key (cart ID and product ID) identifying the cart-product relationship.
     * @return True if the product exists in the cart, otherwise false.
     */
    public boolean productExist(CartProductId cartProductId) {
        return cartProductDao.existsById(cartProductId);
    }

    /**
     * Saves or updates a cart-product relationship.
     *
     * @param cartProduct The cart-product relationship to save or update.
     */
    public void save(CartProduct cartProduct) {
        cartProductDao.save(cartProduct);
    }
}