package com.nitish.covid19.testapp.service;

import com.nitish.covid19.testapp.exception.NoMoreTestsAvailableForTheDayException;
import com.nitish.covid19.testapp.pojo.Patient;
import com.nitish.covid19.testapp.pojo.Test;
import com.nitish.covid19.testapp.pojo.TestDates;
import com.nitish.covid19.testapp.repository.PatientRepository;
import com.nitish.covid19.testapp.repository.TestDatesRepository;
import com.nitish.covid19.testapp.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class TestDateServiceImpl implements TestDateService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    TestDatesRepository testDatesRepository;

    @Autowired
    private JavaMailSender javaMailSender;

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
        sendEmail(testDates, patient.get());
    }

    void sendEmail(TestDates t, Patient p) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(p.getUsername());

        msg.setSubject("Covid-19 Test booking");
        msg.setText("Hello " + p.getFirstName() + ",\nYour Covid-19 test has been booked for the date "+t.getDate()+".\nStay home. Stay safe.\n\nBest,\nCovid-19 Testing Center");

        javaMailSender.send(msg);
    }

    @Override
    public void updateTest(TestDates testDates) {
        testDatesRepository.save(testDates);
    }

    @Override
    public List<TestDates> getTestDates() {
        return testDatesRepository.findAll();
    }
}
