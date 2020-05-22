package com.nitish.covid19.testapp.controller;

import com.nitish.covid19.testapp.pojo.TestDates;
import com.nitish.covid19.testapp.service.TestDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/testdate")
public class TestDatesController {

    @Autowired
    TestDateService testDateService;

    @GetMapping("/getTest")
    @Secured({"ROLE_ADMIN"})
    public List<TestDates> getTestDates(){
        return testDateService.getTestDates();
    }

    @PostMapping("/book")
    @Secured({"ROLE_USER"})
    public void bookTest(@RequestBody TestDates testDates){
        try {
            testDateService.createTestDates(testDates);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @PutMapping("/update")
    @Secured({"ROLE_ADMIN"})
    public void updateTest(@Valid @RequestBody TestDates testDates){
        try {
            testDateService.updateTest(testDates);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
