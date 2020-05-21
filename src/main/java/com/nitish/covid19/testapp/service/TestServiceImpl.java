package com.nitish.covid19.testapp.service;

import com.nitish.covid19.testapp.pojo.Patient;
import com.nitish.covid19.testapp.pojo.Test;
import com.nitish.covid19.testapp.repository.PatientRepository;
import com.nitish.covid19.testapp.repository.TestRepository;
import com.nitish.covid19.testapp.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TestServiceImpl implements TestService{

    @Autowired
    TestRepository testRepository;

    @Autowired
    PatientRepository patientRepository;

    @Override
    public List<Test> getTest() {
        return testRepository.findAll();
    }

    @Override
    public Set<Test> getTestById(int id) {
        Set<Test> test = testRepository.findByPatientId(id);
        return test;
    }

    @Override
    public void createTest(Test test) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails user = (MyUserDetails) auth.getPrincipal();
        Optional<Patient> patient = patientRepository.findByUsername(user.getUsername());
        Test t = new Test(patient.get());

        //patient.get().addTest(t);
        t.setPatient(patient.get());
        testRepository.save(t);
        //test.getPatient().addTest(test);
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
