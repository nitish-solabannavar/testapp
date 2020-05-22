package com.nitish.covid19.testapp.service;

import com.nitish.covid19.testapp.exception.NoMoreTestsAvailableForTheDayException;
import com.nitish.covid19.testapp.pojo.TestDates;

import java.util.List;

public interface TestDateService {

    void createTestDates(TestDates testDates) throws NoMoreTestsAvailableForTheDayException;

    void updateTest(TestDates testDates);

    List<TestDates> getTestDates();
}
