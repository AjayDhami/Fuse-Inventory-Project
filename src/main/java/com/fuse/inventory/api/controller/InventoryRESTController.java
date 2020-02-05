package com.fuse.inventory.api.controller;

import com.fuse.inventory.api.model.Inventory;
import com.fuse.inventory.api.service.InventoryService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryRESTController {

    @Autowired
    InventoryService inventoryService;

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryRESTController.class);

    @PostMapping("/saveItem")
    @ApiOperation(value = "Save new Item to Inventory")
    public Inventory saveItem(@RequestBody Inventory item) {
        LOGGER.info("Inside saveItem(). Item: " + item);
        return inventoryService.saveItem(item);
    }

    @GetMapping("/getAllItems")
    @ApiOperation(value = "List all items and their corresponding details from the Inventory")
    public List<Inventory> getAllItems() {
        return inventoryService.getAllItems();
    }

    @GetMapping("/getItemById/{id}")
    @ApiOperation(value = "Get particular Item and its details from Inventory using provided Item ID")
    public Inventory getItemById(@PathVariable("id") int itemId) {
        LOGGER.info("Inside getItemById(). Item Id: " + itemId);
        return inventoryService.getItemById(itemId);
    }

    @PutMapping("/updateItem/{id}")
    @ApiOperation(value = "Update particular Item Details by using provided Item ID and save that Item to Inventory")
    public Inventory updateItem(@PathVariable("id") int itemId, @RequestBody Inventory item) {
        LOGGER.info("Inside updateItem(). Inventory: " + item);
        return inventoryService.updateItem(itemId, item);
    }

    @DeleteMapping("deleteItem/{id}")
    @ApiOperation(value = "Delete particular Item and its details from Inventory using provided Item ID")
    public void deleteItem(@PathVariable("id") int itemId) {
        LOGGER.info("Inside deleteItem(). Item Id: " + itemId);
        inventoryService.deleteItem(itemId);
    }

    /* Searching items by name of the item*/
    @ApiOperation(value = "Search Items from Inventory using provided Item Name and display those results")
    @GetMapping("/searchItemsByName/{name}")
    public List<Inventory> searchItemsByName(@PathVariable("name") String name) {
        LOGGER.info("Inside searchItemsByName(). Item Name: " + name);
        return inventoryService.searchItemsByName(name);
    }

    /* Searching items by inventory type */
    @GetMapping("/searchItemsByInventoryType/{name}")
    @ApiOperation(value = "Search Items from Inventory using provided Inventory Type and display those results")
    public List<Inventory> searchItemsByInventoryType(@PathVariable("name") String name) {
        LOGGER.info("Inside searchItemsByInventoryType(). Item Name: " + name);
        return inventoryService.searchItemsByInventoryType(name);
    }


    /* Searching quantity of particular item by its name */
    @GetMapping("/searchQuantityOfParticularItem/{name}")
    @ApiOperation(value = "Search Quantity from Inventory of particular Item and display the result")
    public String searchQuantityOfParticularItem(@PathVariable("name") String name) {
        LOGGER.info("Inside searchQuantityOfParticularItem(). Item Name: " + name);
        int leftAmount = inventoryService.searchQuantityOfParticularItem(name);
        String msg = "Quantity of " + name + " left in the inventory is: " + leftAmount;
        return msg;
    }

    /*View the Paginated List of Items in the inventory*/
    @GetMapping("/getAllItemsByPages/{pageNumber}/{numberOfElementsPerPage}/{sortBy}")
    @ApiOperation(value = "View the Paginated List of Items in the inventory")
    public Page<Inventory> getAllItemsByPages(@PathVariable("pageNumber") String pageNumber, @PathVariable("numberOfElementsPerPage") String numberOfElementsPerPage, @PathVariable("sortBy") String sortBy) {
        return inventoryService.getAllItemsByPages(Integer.parseInt(pageNumber), Integer.parseInt(numberOfElementsPerPage), sortBy);
    }

}
