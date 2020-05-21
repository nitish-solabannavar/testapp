package com.nitish.covid19.testapp.exception;

public class UsernameExistsException extends Exception {
    public UsernameExistsException(String errorMessage)
    {
        super(errorMessage);
    }
}
