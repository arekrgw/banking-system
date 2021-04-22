package com.bank.sys.utils;

public class NotEnoughMoneyException extends Exception {
    public NotEnoughMoneyException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
