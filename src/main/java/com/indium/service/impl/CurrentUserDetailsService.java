package com.indium.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.indium.domain.CurrentUser;
import com.indium.domain.User;
import com.indium.service.UserService;

@Service
public class CurrentUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public CurrentUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CurrentUser loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.getUserByName(userName)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username=%s was not found", userName)));
        return new CurrentUser(user);
    }
}
