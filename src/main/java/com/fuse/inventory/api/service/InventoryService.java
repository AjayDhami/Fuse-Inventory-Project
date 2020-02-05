package com.fuse.inventory.api.service;

import com.fuse.inventory.api.model.Inventory;

import java.util.List;

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


    /* Searching Quantity of particular Item by using provided item name */
    int searchQuantityOfParticularItem(String name);
}
