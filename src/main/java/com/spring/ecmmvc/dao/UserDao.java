package com.spring.ecmmvc.dao;

import com.spring.ecmmvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * UserDao is a Spring Data JPA repository interface for managing CRUD operations
 * on the {@link User} entity. It extends {@link JpaRepository} to inherit standard
 * methods for interacting with the database.
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    /**
     * Finds a user by their email address.
     *
     * @param username The email address of the user to find.
     * @return An optional containing the user if found, otherwise empty.
     */
    Optional<User> findUserByEmail(String username);


}