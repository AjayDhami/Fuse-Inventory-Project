package com.fuse.inventory.api.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message,int userId){
        super(message+" of id: "+userId);
    }
}
