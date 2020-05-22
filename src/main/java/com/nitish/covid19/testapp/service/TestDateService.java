package com.nitish.covid19.testapp.service;

import com.nitish.covid19.testapp.exception.NoMoreTestsAvailableForTheDayException;
import com.nitish.covid19.testapp.pojo.TestDates;

public interface TestDateService {

    void createTestDates(TestDates testDates) throws NoMoreTestsAvailableForTheDayException;
}
