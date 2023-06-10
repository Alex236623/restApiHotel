package com.hotel.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResourceCanNotBeChangeException extends RuntimeException{

    private final transient String resourceName;
    private final transient String fieldName;
    private final transient Object fieldValue;

    public ResourceCanNotBeChangeException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s Cant be changed with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        log.info(String.format("%s Cant be changed with %s : '%s'", resourceName, fieldName, fieldValue));
    }


    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
