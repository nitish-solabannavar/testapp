package com.nitish.covid19.testapp.exception;

public class NoMoreTestsAvailableForTheDayException extends Exception {
    public NoMoreTestsAvailableForTheDayException(String message) {
        super(message);
    }
}
