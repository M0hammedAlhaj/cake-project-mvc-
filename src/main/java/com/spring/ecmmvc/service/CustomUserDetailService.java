package com.spring.ecmmvc.service;

import com.spring.ecmmvc.dao.UserDao;
import com.spring.ecmmvc.model.CustomUserDetail;
import com.spring.ecmmvc.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * CustomUserDetailService is a custom implementation of the {@link UserDetailsService} interface,
 * which loads user-specific data for authentication and authorization purposes.
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserDao userDao;

    /**
     * Constructor for CustomUserDetailService.
     *
     * @param userDao The DAO for accessing user data.
     */
    public CustomUserDetailService(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Loads user details by the provided username (email).
     *
     * @param username The email address of the user to load.
     * @return The user details for the specified username.
     * @throws UsernameNotFoundException If the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userDao.findUserByEmail(username);
        user.orElseThrow(() -> new UsernameNotFoundException(username));

        return user.map(CustomUserDetail::new).get();
    }
}