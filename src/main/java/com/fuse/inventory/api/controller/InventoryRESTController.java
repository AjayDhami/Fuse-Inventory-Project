package com.fuse.inventory.api.controller;

import com.fuse.inventory.api.model.Inventory;
import com.fuse.inventory.api.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryRESTController {

    @Autowired
    InventoryService inventoryService;

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryRESTController.class);

    @PostMapping("/saveItem")
    public Inventory saveItem(@RequestBody Inventory item) {
        LOGGER.info("Inside saveUser(). User: " + item);
        return inventoryService.saveItem(item);
    }

    @GetMapping("/getAllItems")
    public List<Inventory> getAllItems() {
        return inventoryService.getAllItems();
    }

    @GetMapping("/getItemById/{id}")
    public Inventory getItemById(@PathVariable("id") int itemId) {
        LOGGER.info("Inside getItemById(). Item Id: " + itemId);
        return inventoryService.getItemById(itemId);
    }

    @PutMapping("/updateItem/{id}")
    public Inventory updateItem(@PathVariable("id") int itemId, @RequestBody Inventory item) {
        LOGGER.info("Inside updateItem(). Inventory: " + item);
        return inventoryService.updateItem(itemId, item);
    }

    @DeleteMapping("deleteItem/{id}")
    public void deleteItem(@PathVariable("id") int itemId) {
        LOGGER.info("Inside deleteItem(). Item Id: " + itemId);
        inventoryService.deleteItem(itemId);
    }

    /* Searching items by name of the item*/
    @GetMapping("/searchItemsByName/{name}")
    public List<Inventory> searchItemsByName(@PathVariable("name") String name) {
        LOGGER.info("Inside searchItemsByName(). Item Name: " + name);
        return inventoryService.searchItemsByName(name);
    }

    /* Searching items by inventory type */
    @GetMapping("/searchItemsByInventoryType/{name}")
    public List<Inventory> searchItemsByInventoryType(@PathVariable("name") String name) {
        LOGGER.info("Inside searchItemsByInventoryType(). Item Name: " + name);
        return inventoryService.searchItemsByInventoryType(name);
    }


    /* Searching quantity of particular item by its name */
    @GetMapping("/searchQuantityOfParticularItem/{name}")
    public String searchQuantityOfParticularItem(@PathVariable("name") String name) {
        LOGGER.info("Inside searchQuantityOfParticularItem(). Item Name: " + name);
        int leftAmount = inventoryService.searchQuantityOfParticularItem(name);
        String msg = "Quantity of " + name + " left in the inventory is: " + leftAmount;
        return msg;
    }
}
