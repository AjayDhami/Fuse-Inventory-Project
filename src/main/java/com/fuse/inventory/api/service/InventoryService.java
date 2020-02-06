package com.fuse.inventory.api.service;

import com.fuse.inventory.api.model.Inventory;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface InventoryService {

    /*Save new Item to the Inventory*/
    Inventory saveItem(Inventory item);

    /*List all items and their corresponding details from the Inventory*/
    List<Inventory> getAllItems();

    /*Get particular Item and its details from Inventory using provided Item ID*/
    Inventory getItemById(int itemId);

    /*Update particular Item Details by using provided Item ID and save that Item to Inventory*/
    Inventory updateItem(int itemId, Inventory item);

    /*Delete particular Item and its details from Inventory using provided Item ID*/
    void deleteItem(int itemId);

    /* Search item by Item Name */
    List<Inventory> searchItemsByName(String name);

    /* Search item by Inventory Type */
    List<Inventory> searchItemsByInventoryType(String name);

    /* Search item by Quantity of Item */
    List<String> searchItemsByQuantity(int quantityOfItem);

    /*Display the name of items and frequency of those items*/
    Map<String, String> showItemsNameAndQuantityOfItem();

    /*Search Quantity of Item using provided Item Name*/
    int searchQuantityOfParticularItem(String name);

    /*Update Quantity of Particular Item in the inventory*/
    String updateQuantityOfParticularItem(int userId, String itemName, int newValueOfItem);

    /*View the Paginated List of Items in the inventory*/
    Page<Inventory> getAllItemsByPages(int pageNumber, int numberOfElementsPerPage, String sortBy);
}
