package org.idk.newsagency.exception;

public class EntityAlreadyExistsException extends RuntimeException {

    private static String obtainMessage(String id) {
        return String.format("User with id %s already exists", id);
    }

    ;public EntityAlreadyExistsException(String id) {
        super(obtainMessage(id));
    }
}
