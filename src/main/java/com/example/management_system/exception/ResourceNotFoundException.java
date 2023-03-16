package com.example.management_system.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{


    public ResourceNotFoundException(String resourceName,String fieldName, long fieldValue){
        super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue));
    }

    public ResourceNotFoundException(String resourceName){
        super(String.format("%s not found", resourceName));
    }


}
