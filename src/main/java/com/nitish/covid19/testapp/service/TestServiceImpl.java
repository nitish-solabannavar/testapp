package com.nitish.covid19.testapp.service;

import com.nitish.covid19.testapp.pojo.Test;
import com.nitish.covid19.testapp.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestServiceImpl implements TestService{

    @Autowired
    TestRepository testRepository;

    @Override
    public List<Test> getTest() {
        return testRepository.findAll();
    }

    @Override
    public Test getTestById(int id) {
        Optional<Test> test = testRepository.findById(id);
        return test.get();
    }

    @Override
    public void createTest(Test test) {
        testRepository.save(test);
    }

    @Override
    public void updateTest(Test test) {
        testRepository.save(test);
    }

    @Override
    public void deleteTest(int id) {
        testRepository.deleteById(id);
    }
}
