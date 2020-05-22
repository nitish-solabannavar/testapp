package com.nitish.covid19.testapp.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TestDates {

    @Id
    private LocalDate date;

    @JsonIgnore
    private int count;

    @JsonIgnore
    @OneToMany(mappedBy = "testDates", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<Test> test;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Test> getTest() {
        return test;
    }

    //public void setTest(List<Test> test) {
      //  this.test = test;
    //}

    public void addTest(Test t){
        if(this.test == null){
            this.test = new ArrayList<>();
        }

        this.test.add(t);
        t.setTestDates(this);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
