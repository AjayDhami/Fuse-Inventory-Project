package com.fuse.inventory.api.service;

import com.fuse.inventory.api.exception.UserNotFoundException;
import com.fuse.inventory.api.model.User;
import com.fuse.inventory.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException("User not found", userId);
        }
        return optionalUser.get();
    }

    @Override
    public User updateUser(int userId, User user) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User newUser = optionalUser.get();
            newUser.setName(user.getName());
            newUser.setEmail(user.getEmail());
            newUser.setInventories(user.getInventories());

            return userRepository.save(newUser);
        } else {
            return userRepository.save(user);
        }
    }

    @Override
    public void deleteUser(int userId) {
        User userToBeDeleted = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found", userId));
        userRepository.delete(userToBeDeleted);
    }
}
