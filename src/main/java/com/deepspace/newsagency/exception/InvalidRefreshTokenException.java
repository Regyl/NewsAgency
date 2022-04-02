package com.deepspace.newsagency.exception;

public class InvalidRefreshTokenException extends RuntimeException {

    public InvalidRefreshTokenException(String msg) {
        super(msg);
    }
}
