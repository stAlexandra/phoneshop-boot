package com.expertsoft.phoneshop.exception;

public class PhoneNotFoundException extends RuntimeException {
    public PhoneNotFoundException() {
        super();
    }

    public PhoneNotFoundException(String phoneId) {
        super("Could not find phone by id " + phoneId);
    }
}
