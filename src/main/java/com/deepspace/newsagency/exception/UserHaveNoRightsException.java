package com.deepspace.newsagency.exception;

public class UserHaveNoRightsException extends RuntimeException {

    public UserHaveNoRightsException(String roleName) {
        super(roleName);
    }
}
