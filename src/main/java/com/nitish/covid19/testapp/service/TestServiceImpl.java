package com.nitish.covid19.testapp.service;

import com.nitish.covid19.testapp.exception.NoMoreTestsAvailableForTheDayException;
import com.nitish.covid19.testapp.pojo.Patient;
import com.nitish.covid19.testapp.pojo.Test;
import com.nitish.covid19.testapp.pojo.TestDates;
import com.nitish.covid19.testapp.repository.PatientRepository;
import com.nitish.covid19.testapp.repository.TestDatesRepository;
import com.nitish.covid19.testapp.repository.TestRepository;
import com.nitish.covid19.testapp.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TestServiceImpl implements TestService{

    @Autowired
    TestRepository testRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    TestDatesRepository testDatesRepository;

    @Autowired
    private JavaMailSender javaMailSender;


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
    public void createTest(Test test) throws NoMoreTestsAvailableForTheDayException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails user = (MyUserDetails) auth.getPrincipal();

        Optional<Patient> patient = patientRepository.findByUsername(user.getUsername());

        TestDates testDates = test.getTestDates();

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
     //   patient.get().addTest(test);
        testDatesRepository.save(testDates);
    }

    @Override
    public void updateTest(Test test) {
        Optional<Test> t = testRepository.findById(test.getTestId());
        test.setPatient(t.get().getPatient());
        testRepository.save(test);
        sendEmail(test);
    }

    void sendEmail(Test t) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(t.getPatient().getUsername());

        msg.setSubject("Covid-19 Test results");
        if(t.getResult().equals("Negative")) {
            msg.setText("Hello " + t.getPatient().getFirstName() + ",\nYour Covid-19 test result came out to be Negative.\nStay home. Stay safe.\n\nBest,\nCovid-19 Testing Center");
        }
        else{
            msg.setText("Hello " + t.getPatient().getFirstName() + ",\nYour Covid-19 test result came out to be Positive.\nVisit a nearby hospital if you show any symptom.\nStay safe!!\n\nBest,\nCovid-19 Testing Center");
        }

        javaMailSender.send(msg);
    }

    @Override
    public void deleteTest(int id) {
        testRepository.deleteById(id);
    }
}
