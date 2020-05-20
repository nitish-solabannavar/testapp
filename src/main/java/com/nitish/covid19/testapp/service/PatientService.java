package com.nitish.covid19.testapp.service;

import com.nitish.covid19.testapp.exception.UpdateNotAuthorizedException;
import com.nitish.covid19.testapp.pojo.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> getPatient();
    void createPatient(Patient patient);
    void updatePatient(Patient patient) throws UpdateNotAuthorizedException;
    Patient getPatientById(int id);
    void deletePatient(int id);
}
