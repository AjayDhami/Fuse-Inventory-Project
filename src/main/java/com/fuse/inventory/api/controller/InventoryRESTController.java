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
import java.util.Map;

@RestController
public class InventoryRESTController {

    @Autowired
    InventoryService inventoryService;

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryRESTController.class);

    /*Save new Item to the Inventory*/
    @PostMapping("/saveItem")
    @ApiOperation(value = "Save new Item to the Inventory")
    public Inventory saveItem(@RequestBody Inventory item) {
        LOGGER.info("Inside saveItem(). Item to be saved is: " + item);
        return inventoryService.saveItem(item);
    }

    /*List all items and their corresponding details from the Inventory*/
    @GetMapping("/getAllItems")
    @ApiOperation(value = "List all items and their corresponding details from the Inventory")
    public List<Inventory> getAllItems() {
        return inventoryService.getAllItems();
    }

    /*Get particular Item and its details from Inventory using provided Item ID*/
    @GetMapping("/getItemById/{id}")
    @ApiOperation(value = "Get particular Item and its details from Inventory using provided Item ID")
    public Inventory getItemById(@PathVariable("id") int itemId) {
        LOGGER.info("Inside getItemById(). Get Item details of Item whose Id is: " + itemId);
        return inventoryService.getItemById(itemId);
    }

    /*Update particular Item Details by using provided Item ID and save that Item to Inventory*/
    @PutMapping("/updateItem/{id}")
    @ApiOperation(value = "Update particular Item Details by using provided Item ID and save that Item to Inventory")
    public Inventory updateItem(@PathVariable("id") int itemId, @RequestBody Inventory item) {
        LOGGER.info("Inside updateItem(). Update item details of item with Id: " + itemId);
        LOGGER.info("Inside updateItem(). New Item Details to be updated is: " + item);
        return inventoryService.updateItem(itemId, item);
    }

    /*Delete particular Item and its details from Inventory using provided Item ID*/
    @DeleteMapping("deleteItem/{id}")
    @ApiOperation(value = "Delete particular Item and its details from Inventory using provided Item ID")
    public void deleteItem(@PathVariable("id") int itemId) {
        LOGGER.info("Inside deleteItem(). Delete Item whose Item Id is: " + itemId);
        inventoryService.deleteItem(itemId);
    }

    /* Search item by Item Name */
    @ApiOperation(value = "Search Items from Inventory using provided Item Name and display those results")
    @GetMapping("/searchItemsByName/{name}")
    public List<Inventory> searchItemsByName(@PathVariable("name") String name) {
        LOGGER.info("Inside searchItemsByName(). Search the Items with Name: " + name);
        return inventoryService.searchItemsByName(name);
    }

    /* Search item by Inventory Type */
    @GetMapping("/searchItemsByInventoryType/{name}")
    @ApiOperation(value = "Search Items from Inventory using provided Inventory Type and display those results")
    public List<Inventory> searchItemsByInventoryType(@PathVariable("name") String name) {
        LOGGER.info("Inside searchItemsByInventoryType(). Search the Items with Inventory Type: " + name);
        return inventoryService.searchItemsByInventoryType(name);
    }


    /* Search item by Quantity of Item */
    @GetMapping("/searchItemsByQuantity/{quantityOfItem}")
    @ApiOperation(value = "Search Items from Inventory using provided Quantity and display the corresponding items(s)")
    public List<String> searchItemsByQuantity(@PathVariable("quantityOfItem") String quantityOfItem) {
        LOGGER.info("Inside searchItemsByInventoryType(). Search the Item with Quantity of Item: " + quantityOfItem);
        return inventoryService.searchItemsByQuantity(Integer.parseInt(quantityOfItem));
    }

    /*Display the name of items and frequency of those items*/
    @GetMapping("/showItemsNameAndQuantityOfItem")
    @ApiOperation(value = "Display items and corresponding frequency of those items in the inventory")
    public Map<String, String> showItemsNameAndQuantityOfItem() {
        LOGGER.info("Inside showItemsNameAndQuantityOfItem(). Display the items and their corresponding quantities");
        return inventoryService.showItemsNameAndQuantityOfItem();
    }

    /*Search Quantity of Item using provided Item Name*/
    @GetMapping("/searchQuantityOfParticularItem/{name}")
    @ApiOperation(value = "Search Quantity from Inventory of particular Item and display the result")
    public String searchQuantityOfParticularItem(@PathVariable("name") String name) {
        LOGGER.info("Inside searchQuantityOfParticularItem(). Search Quantity of Item with Name: " + name);
        int leftAmount = inventoryService.searchQuantityOfParticularItem(name);
        return "Quantity of " + name + " left in the inventory is: " + leftAmount;
    }

    /*Update Quantity of Particular Item in the inventory*/
    @PutMapping("updateQuantityOfParticularItem/{userId}/{itemName}/{newValueOfItem}")
    @ApiOperation(value = "Update the quantity of particular item")
    public String updateQuantityOfParticularItem(@PathVariable("userId") String userId, @PathVariable("itemName") String itemName, @PathVariable("newValueOfItem") String newValueOfItem) {
        LOGGER.info("Inside updateQuantityOfParticularItem(). Item Name is: " + itemName + " and its Quantity is to be updated to :" + newValueOfItem);
        return inventoryService.updateQuantityOfParticularItem(Integer.parseInt(userId), itemName, Integer.parseInt(newValueOfItem));
    }

    /*View the Paginated List of Items in the inventory*/
    @GetMapping("/getAllItemsByPages/{pageNumber}/{numberOfElementsPerPage}/{sortBy}")
    @ApiOperation(value = "View the Paginated List of Items in the inventory")
    public Page<Inventory> getAllItemsByPages(@PathVariable("pageNumber") String pageNumber, @PathVariable("numberOfElementsPerPage") String numberOfElementsPerPage, @PathVariable("sortBy") String sortBy) {
        LOGGER.info("Inside getAllItemsByPages(). Show the paginated list of items with " + numberOfElementsPerPage + " items per page and sort the page by " + sortBy + " . Currently showing page is: " + pageNumber);
        return inventoryService.getAllItemsByPages(Integer.parseInt(pageNumber), Integer.parseInt(numberOfElementsPerPage), sortBy);
    }

}
