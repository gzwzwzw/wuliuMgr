package com.logistics.exception;

public class ResourceNotFoundException extends RuntimeException {

    private final String resourceName;
    private final Long id;

    /**
     * @param resourceName 资源类型（如"Order"）
     * @param id          资源ID
     */
    public ResourceNotFoundException(String resourceName, Long id) {
        super(String.format("%s with id %d not found", resourceName, id));
        this.resourceName = resourceName;
        this.id = id;
    }
}