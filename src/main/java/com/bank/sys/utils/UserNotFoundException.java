package com.bank.sys.utils;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
