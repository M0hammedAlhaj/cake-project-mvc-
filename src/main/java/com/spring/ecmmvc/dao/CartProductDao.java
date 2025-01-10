package com.spring.ecmmvc.dao;

import com.spring.ecmmvc.model.CartProduct;
import com.spring.ecmmvc.model.CartProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * CartProductDao is a Spring Data JPA repository interface for managing CRUD operations
 * on the {@link CartProduct} entity. It extends {@link JpaRepository} to inherit standard
 * methods for interacting with the database.
 */
@Repository
public interface CartProductDao extends JpaRepository<CartProduct, CartProductId> {

    /**
     * Deletes cart products associated with a specific product ID.
     *
     * @param productId The ID of the product to delete associated cart products.
     */
    void deleteCartProductsByProductId(int productId);

    /**
     * Retrieves all cart products associated with a specific cart ID.
     *
     * @param cartId The ID of the cart to retrieve associated cart products.
     * @return A set of cart products associated with the specified cart ID.
     */
    Set<CartProduct> getAllByCartId(int cartId);

    Optional<CartProduct> findCartProductsByCartIdAndProductId(int cartId, int productId);

}