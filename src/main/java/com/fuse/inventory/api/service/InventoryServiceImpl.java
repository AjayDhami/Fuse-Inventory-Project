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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        String addedBy = setUserNameUsingUserId(item);
        item.setAddedby(addedBy);
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
    public Inventory updateItem(int itemId, Inventory item) {

        Optional<Inventory> optionalInventory = inventoryRepository.findById(itemId);
        if (optionalInventory.isPresent()) {
            Inventory newItem = optionalInventory.get();
            newItem.setName(item.getName());
            newItem.setType(item.getType());
            newItem.setUid(item.getUid());

            String addedBy = setUserNameUsingUserId(item);
            newItem.setAddedby(addedBy);

            return inventoryRepository.save(newItem);

        } else {
            String addedBy = setUserNameUsingUserId(item);
            item.setAddedby(addedBy);

            return inventoryRepository.save(item);
        }
    }

    @Override
    public void deleteItem(int itemId) {
        Inventory itemToBeDeleted = inventoryRepository.findById(itemId).orElseThrow(() -> new ItemNotFoundException("Item not found ", itemId));
        inventoryRepository.delete(itemToBeDeleted);
    }

    @Override
    public List<Inventory> searchItemsByName(String name) {
        List<Inventory> searchedItemsByName = inventoryRepository.searchItemsByName(name);
        return searchedItemsByName;
    }

    @Override
    public List<Inventory> searchItemsByInventoryType(String name) {
        List<Inventory> searchedItemsByInventoryType = inventoryRepository.searchItemsByInventoryType(name);
        return searchedItemsByInventoryType;
    }

    @Override
    public Map<String,String> searchItemsByQuantity() {
       Map<String,String> map=new HashMap<>();
       for(Inventory inventory:inventoryRepository.searchItemsByQuantity()){
           String key=inventory.getName();
           String value=inventory.getType();

           map.put(key,value);
       }
       return map;
    }

    @Override
    public int searchQuantityOfParticularItem(String name) {
        int searchedQuantityOfParticularItem = inventoryRepository.searchQuantityOfParticularItem(name);
        return searchedQuantityOfParticularItem;
    }


    /*Set user name by using user id obtained from inventory*/
    private String setUserNameUsingUserId(Inventory item) {
        User userDetails = userRepository.findUserById(item.getUid());
        LOGGER.info("Inside saveUser(). Details of User is: " + userDetails);
        if (userDetails == null) {
            throw new UserNotFoundException("Item can't be added to inventory since User is not found", item.getUid());
        }
        String addedByUserName = userDetails.getName();
        String addedByMessage = addedByUserName + " has added a new " + item.getName() + " in the " + item.getType() + " inventory.";

        return addedByMessage;
    }
}
