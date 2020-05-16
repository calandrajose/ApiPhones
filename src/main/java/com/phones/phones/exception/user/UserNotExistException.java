package com.phones.phones.exception.user;

public class UserNotExistException extends Exception {

    public UserNotExistException(String errMsg) {
        super(errMsg);
    }
}
