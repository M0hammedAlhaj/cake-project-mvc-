package com.spring.ecmmvc.service;

import com.spring.ecmmvc.dao.UserDao;
import com.spring.ecmmvc.model.Cart;
import com.spring.ecmmvc.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * UserService provides business logic for managing users, including registration
 * and retrieval of user details.
 */
@Service
public class UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor for UserService.
     *
     * @param userDao         The DAO for accessing user data.
     * @param passwordEncoder The password encoder for securely hashing passwords.
     */
    public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user by encoding their password and associating a new cart with them.
     *
     * @param user The user to register.
     * @return The registered user.
     */
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.cart = new Cart();
        return userDao.save(user);
    }

    /**
     * Finds a user by their email address.
     *
     * @param email The email address of the user to find.
     * @return The user with the specified email address.
     * @throws RuntimeException If the user is not found.
     */
    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
}