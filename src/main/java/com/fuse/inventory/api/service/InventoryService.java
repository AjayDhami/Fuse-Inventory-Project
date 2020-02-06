package com.fuse.inventory.api.service;

import com.fuse.inventory.api.model.Inventory;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface InventoryService {

    Inventory saveItem(Inventory item);

    List<Inventory> getAllItems();

    Inventory getItemById(int itemId);

    Inventory updateItem(int itemId, Inventory item);

    void deleteItem(int itemId);

    /* Searching items by name */
    List<Inventory> searchItemsByName(String name);

    /* Searching items by type */
    List<Inventory> searchItemsByInventoryType(String name);

    /*Display the name of item and frequency of that item in inventory*/
    Map<String,String> searchItemsByQuantity();


    /* Searching Quantity of particular Item by using provided item name */
    int searchQuantityOfParticularItem(String name);

    /*View the Paginated List of Items in the inventory*/
    Page<Inventory> getAllItemsByPages(int pageNumber, int numberOfElementsPerPage,String sortBy);
}
