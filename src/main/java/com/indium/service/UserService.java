package com.indium.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.indium.domain.User;
import com.indium.domain.UserCreateForm;

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByName(String userName);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);
    
    User save(User user);

    List<User> getList();
    
    boolean isValidUser(String username, String password);

}
