package com.deepspace.newsagency.exception;

public class EntityAlreadyExistsException extends RuntimeException {

    private static String obtainMessage(Class<?> entity, String id) {
        return String.format("Entity of type %s with id %s already exists", entity.getSimpleName(), id);
    }

    public EntityAlreadyExistsException(Class<?> entity, String id) {
        super(obtainMessage(entity, id));
    }
}
