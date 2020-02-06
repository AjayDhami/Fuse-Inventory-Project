package com.fuse.inventory.api.service;

import com.fuse.inventory.api.model.User;

import java.util.List;

public interface UserService {

    /*Save new user*/
    User saveUser(User user);

    /*List all users and their corresponding details*/
    List<User> getAllUsers();

    /*Get particular User and its details using provided User ID*/
    User getUserById(int userId);

    /*Update particular User Details by using provided User ID and save that User*/
    User updateUser(int userId, User user);

    /*Delete particular User and its details by using provided User ID*/
    void deleteUser(int userId);
}
