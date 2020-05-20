package com.nitish.covid19.testapp.repository;

import com.nitish.covid19.testapp.pojo.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Optional<Patient> findByUsername(String username);
}
