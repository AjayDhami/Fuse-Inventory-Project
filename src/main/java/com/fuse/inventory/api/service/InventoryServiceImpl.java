package com.fuse.inventory.api.service;

import com.fuse.inventory.api.controller.InventoryRESTController;
import com.fuse.inventory.api.exception.ItemNotFoundException;
import com.fuse.inventory.api.exception.UserNotFoundException;
import com.fuse.inventory.api.model.Inventory;
import com.fuse.inventory.api.model.User;
import com.fuse.inventory.api.repository.InventoryRepository;
import com.fuse.inventory.api.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    UserRepository userRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryServiceImpl.class);

    @Override
    public Inventory saveItem(Inventory item) {
        User userDetails = userRepository.findUserById(item.getUid());
        LOGGER.info("Inside saveUser(). Details of User is: " + userDetails);
        if (userDetails == null) {
            throw new UserNotFoundException("Item can't be added to inventory since User is not found", item.getUid());
        }
        String addedByUserName = userDetails.getName();
        String addedByMessage = addedByUserName + " has added a new " + item.getName() + " in the " + item.getType() + " inventory.";
        item.setAddedby(addedByMessage);
        LOGGER.info("Complete Item Details: " + item);
        return inventoryRepository.save(item);
    }

    @Override
    public List<Inventory> getAllItems() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory getItemById(int itemId) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(itemId);
        if (!optionalInventory.isPresent()) {
            throw new ItemNotFoundException("Item not found", itemId);
        }
        return optionalInventory.get();
    }

    @Override
    public Inventory updateItem(Inventory item) {
        return inventoryRepository.save(item);
    }

    @Override
    public void deleteItem(Inventory item) {
        inventoryRepository.delete(item);
    }
}
