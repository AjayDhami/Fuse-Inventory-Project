package com.fuse.inventory.api.controller;

import com.fuse.inventory.api.model.User;
import com.fuse.inventory.api.service.UserService;
import io.swagger.annotations.ApiOperation;
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

    /*Save new user*/
    @PostMapping("/saveUser")
    @ApiOperation(value = "Save new User to User DB")
    public User saveUser(@RequestBody User user) {
        LOGGER.info("Inside saveUser(). User: " + user);
        return userService.saveUser(user);
    }

    /*List all the users and their corresponding details*/
    @GetMapping("/getAllUsers")
    @ApiOperation(value = "List all users and their corresponding details from the User DB")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /*Get particular User and its details using provided User ID*/
    @GetMapping("/getUserById/{id}")
    @ApiOperation(value = "Get particular User and its details from User DB using provided User ID")
    public User getUserById(@PathVariable("id") int userId) {
        LOGGER.info("Inside getUserById(). User Id: " + userId);
        return userService.getUserById(userId);
    }

    /*Update particular User Details by using provided User ID and save that User*/
    @PutMapping("/updateUser/{id}")
    @ApiOperation(value = "Update particular User Details by using provided User ID and save that User to User DB")
    public User updateUser(@PathVariable("id") int userId, @RequestBody User user) {
        LOGGER.info("Inside updateUser(). User: " + user);
        return userService.updateUser(userId, user);
    }

    /*Delete particular User and its details by using provided User ID*/
    @DeleteMapping("deleteUser/{id}")
    @ApiOperation(value = "Delete particular User and its details from User DB using provided User ID")
    public void deleteUser(@PathVariable("id") int userId) {
        LOGGER.info("Inside deleteUser(). User Id: " + userId);
        userService.deleteUser(userId);
    }
}
