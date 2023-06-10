package com.hotel.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResourceNotFoundException extends RuntimeException {
    private transient final String resourceName;
    private transient final String fieldName;
    private transient final Object fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        log.info(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
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