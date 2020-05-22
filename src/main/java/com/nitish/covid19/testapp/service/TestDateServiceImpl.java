package com.nitish.covid19.testapp.service;

import com.nitish.covid19.testapp.exception.NoMoreTestsAvailableForTheDayException;
import com.nitish.covid19.testapp.pojo.Patient;
import com.nitish.covid19.testapp.pojo.Test;
import com.nitish.covid19.testapp.pojo.TestDates;
import com.nitish.covid19.testapp.repository.PatientRepository;
import com.nitish.covid19.testapp.repository.TestDatesRepository;
import com.nitish.covid19.testapp.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestDateServiceImpl implements TestDateService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    TestDatesRepository testDatesRepository;

    @Override
    public void createTestDates(TestDates testDates) throws NoMoreTestsAvailableForTheDayException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails user = (MyUserDetails) auth.getPrincipal();

        Test test = new Test();

        Optional<Patient> patient = patientRepository.findByUsername(user.getUsername());
        test.setPatient(patient.get());

        Optional<TestDates> opTe = testDatesRepository.findByDate(testDates.getDate());

        if(opTe.isPresent()){
            if(opTe.get().getCount() >= 5){
                throw new NoMoreTestsAvailableForTheDayException("No more tests for the day. Choose another day");
            }
            else {
                testDates.setCount(opTe.get().getCount() + 1);
            }
        }
        else{
            testDates.setCount(1);
        }

        testDates.addTest(test);
        testDatesRepository.save(testDates);
    }
}
