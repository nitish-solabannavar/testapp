package com.nitish.covid19.testapp.controller;

import com.nitish.covid19.testapp.pojo.TestDates;
import com.nitish.covid19.testapp.service.TestDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testdate")
public class TestDatesController {

    @Autowired
    TestDateService testDateService;

    @PostMapping("/book")
    public void bookTest(@RequestBody TestDates testDates){
        try {
            testDateService.createTestDates(testDates);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
