package com.spring.ecmmvc.service;

import com.spring.ecmmvc.dao.UserDao;
import com.spring.ecmmvc.model.CustomUserDetail;
import com.spring.ecmmvc.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private UserDao userDao;

    public CustomUserDetailService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userDao.findUserByEmail(username);
        user.orElseThrow(() -> new UsernameNotFoundException(username));


        return user.map(CustomUserDetail::new).get();
    }

}
