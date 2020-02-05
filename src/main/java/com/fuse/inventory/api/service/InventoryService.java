package com.fuse.inventory.api.service;

import com.fuse.inventory.api.model.Inventory;

import java.util.List;

public interface InventoryService {

    Inventory saveItem(Inventory item);

    List<Inventory> getAllItems();

    Inventory getItemById(int itemId);

    Inventory updateItem(Inventory item);

    void deleteItem(Inventory item);
}
