package com.nitish.covid19.testapp.service;

import com.nitish.covid19.testapp.exception.NoMoreTestsAvailableForTheDayException;
import com.nitish.covid19.testapp.pojo.Test;

import java.util.List;
import java.util.Set;

public interface TestService {
    List<Test> getTest();
    Test getTestById(int id);
    void createTest(Test test) throws NoMoreTestsAvailableForTheDayException;
    void updateTest(Test test);
    void deleteTest(int id);
}
