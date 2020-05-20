package com.nitish.covid19.testapp.repository;

import com.nitish.covid19.testapp.pojo.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
