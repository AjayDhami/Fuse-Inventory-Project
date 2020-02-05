package com.fuse.inventory.api.controller;

import com.fuse.inventory.api.model.User;
import com.fuse.inventory.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRESTController {

    @Autowired
    UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRESTController.class);

    @PostMapping("/saveUser")
    public User saveUser(@RequestBody User user) {
        LOGGER.info("Inside saveUser(). User: " + user);
        return userService.saveUser(user);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") int userId) {
        LOGGER.info("Inside getUserById(). User Id: " + userId);
        return userService.getUserById(userId);
    }

    @PutMapping("/updateUser/{id}")
    public User updateUser(@PathVariable("id") int userId, @RequestBody User user) {
        LOGGER.info("Inside updateUser(). User: " + user);
        return userService.updateUser(userId, user);
    }

    @DeleteMapping("deleteUser/{id}")
    public void deleteUser(@PathVariable("id") int userId) {
        LOGGER.info("Inside deleteUser(). User Id: " + userId);
        userService.deleteUser(userId);
    }
}
