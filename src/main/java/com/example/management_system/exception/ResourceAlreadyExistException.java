package com.example.management_system.exception;

public class ResourceAlreadyExistException extends RuntimeException{
    public ResourceAlreadyExistException(String resourceName,String fieldName, String fieldValue){
        super(String.format("%s already exists with %s : %s", resourceName, fieldName, fieldValue));
    }

}
