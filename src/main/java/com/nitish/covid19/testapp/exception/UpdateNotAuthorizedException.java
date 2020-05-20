package com.nitish.covid19.testapp.exception;

public class UpdateNotAuthorizedException extends Exception {
    public UpdateNotAuthorizedException(String errorMessage)
    {
        super(errorMessage);
    }
}

