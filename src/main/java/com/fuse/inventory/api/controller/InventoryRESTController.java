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
    public Inventory saveItem(@RequestBody Inventory inventory) {
        LOGGER.info("Inside saveUser(). User: " + inventory);
        return inventoryService.saveItem(inventory);
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
    public Inventory updateItem(@PathVariable("id") int itemId, @RequestBody Inventory inventory) {
        LOGGER.info("Inside updateItem(). Inventory: " + inventory);
        Inventory updateInventory = inventoryService.getItemById(itemId);
        return inventoryService.updateItem(updateInventory);
    }

    @DeleteMapping("deleteItem/{id}")
    public void deleteItem(@PathVariable("id") int itemId) {
        LOGGER.info("Inside deleteItem(). Item Id: " + itemId);
        Inventory deleteInventory = inventoryService.getItemById(itemId);
        inventoryService.deleteItem(deleteInventory);
    }
}
