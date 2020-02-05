package com.fuse.inventory.api.service;

import com.fuse.inventory.api.model.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUsers();

    User getUserById(int userId);

    User updateUser(int userId, User user);

    void deleteUser(int userId);
}
