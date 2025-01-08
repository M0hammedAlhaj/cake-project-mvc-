package com.spring.ecmmvc.dao;

import com.spring.ecmmvc.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CartDao is a Spring Data JPA repository interface for managing CRUD operations
 * on the {@link Cart} entity. It extends {@link JpaRepository} to inherit standard
 * methods for interacting with the database.
 */
@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {

}