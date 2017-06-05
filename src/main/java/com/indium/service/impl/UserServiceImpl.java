package com.indium.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.indium.domain.User;
import com.indium.domain.UserCreateForm;
import com.indium.repository.UserRepository;
import com.indium.service.UserService;
import com.indium.service.exception.UserAlreadyExistException;

@Service
@Validated
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(long id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUserByName(String userName) {
        return userRepository.findOneByUsername(userName);
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll(new Sort("username"));
    }

    @Override
    public User create(UserCreateForm form) {
        User user = new User();
        user.setUsername(form.getId());
        user.setPasswordHash(form.getPassword1());
        user.setRole(form.getRole());
        return userRepository.save(user);
    }
    
    @Override
    @Transactional
    public User save(@NotNull @Valid final User user) {
        User existing = userRepository.findOne(user.getId());
        if (existing != null) {
            throw new UserAlreadyExistException(
                    String.format("There already exists a user with id=%s", user.getId()));
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getList() {
        return userRepository.findAll();
    }

    @Override
    public boolean isValidUser(String username, String password) {
    	Optional<User> findUserByIDAndPassword = userRepository.findUserByIDAndPassword(username, password);
    	return findUserByIDAndPassword.isPresent();
    }
}
