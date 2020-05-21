package com.nitish.covid19.testapp.repository;

import com.nitish.covid19.testapp.pojo.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TestRepository extends JpaRepository<Test, Integer> {
    public Set<Test> findByPatientId(int patientId);
}
