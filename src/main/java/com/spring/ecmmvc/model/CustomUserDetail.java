package com.spring.ecmmvc.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * CustomUserDetail is a custom implementation of the {@link UserDetails} interface,
 * which provides user-specific data for authentication and authorization purposes.
 */
public class CustomUserDetail implements UserDetails {

    private User user;

    /**
     * Constructor to initialize the CustomUserDetail with a User object.
     *
     * @param user The user object containing user details.
     */
    public CustomUserDetail(User user) {
        this.user = user;
    }

    /**
     * Returns the authorities granted to the user.
     *
     * @return A collection of granted authorities.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles();
    }

    /**
     * Returns the password used to authenticate the user.
     *
     * @return The user's password.
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Returns the username used to authenticate the user.
     *
     * @return The user's email address.
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }
}