package com.nitish.covid19.testapp.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int testId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private String result;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "test_date")
    private TestDates testDates;

    public Test() {
    }

    public Test(Patient patient) {
        this.patient = patient;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public TestDates getTestDates() {
        return testDates;
    }

    public void setTestDates(TestDates testDates) {
        this.testDates = testDates;
    }

}
