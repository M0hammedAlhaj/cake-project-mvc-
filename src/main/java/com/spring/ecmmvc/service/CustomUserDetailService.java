package com.spring.ecmmvc.service;

import com.spring.ecmmvc.model.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * CustomUserDetailService is a custom implementation of the {@link UserDetailsService} interface,
 * which loads user-specific data for authentication and authorization purposes.
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserService userService;

    /**
     * Constructor for CustomUserDetailService.
     *
     * @param userService The DAO for accessing user data.
     */
    @Autowired
    public CustomUserDetailService(@Lazy UserService userService) {
        this.userService = userService;
    }

    /**
     * Loads user details by the provided username (email).
     *
     * @param email The email address of the user to load.
     * @return The user details for the specified username.
     * @throws UsernameNotFoundException If the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return new CustomUserDetail(userService.findUserByEmail(email));
    }
}