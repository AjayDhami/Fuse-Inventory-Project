package com.fuse.inventory.api.exception;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(String message, int userId) {
        super(message + " of id: " + userId);
    }
}
