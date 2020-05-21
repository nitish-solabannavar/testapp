package com.nitish.covid19.testapp.controller;

import com.nitish.covid19.testapp.pojo.Test;
import com.nitish.covid19.testapp.service.TestService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TestController {

    TestService testService;

    @GetMapping("/get")
    @Secured({"ROLE_ADMIN"})
    public List<Test> getTest(){
        return testService.getTest();
    }

    @GetMapping("/get/{testId}")
    @Secured ({"ROLE_ADMIN"})
    public Test getTestById(@PathVariable int testId){
        return testService.getTestById(testId);
    }

    @PostMapping("/register")
    @Secured ({"ROLE_USER"})
    public void saveTest(@Valid @RequestBody Test test){
        try {
            testService.createTest(test);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @PutMapping("/update")
    @Secured ({"ROLE_ADMIN"})
    public void updateTest(@Valid @RequestBody Test test){
        try {
            testService.updateTest(test);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @DeleteMapping("/delete/{testId}")
    @Secured ({"ROLE_ADMIN"})
    public void deleteTest(@PathVariable int testId){
        testService.deleteTest(testId);
    }
}
