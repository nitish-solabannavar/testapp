package com.nitish.covid19.testapp.service;


import com.nitish.covid19.testapp.exception.UpdateNotAuthorizedException;
import com.nitish.covid19.testapp.exception.UsernameExistsException;
import com.nitish.covid19.testapp.pojo.Patient;
import com.nitish.covid19.testapp.repository.PatientRepository;
import com.nitish.covid19.testapp.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Patient> getPatient() {
        return patientRepository.findAll();
    }

    @Override
    public void createPatient(Patient patient) throws UsernameExistsException {
        Optional<Patient> exist = patientRepository.findByUsername(patient.getUsername());

        if(!exist.isEmpty()){
            throw new UsernameExistsException("This username already exists");
        }

        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        patient.setRole("ROLE_USER");
        patientRepository.save(patient);
    }

    @Override
    public void updatePatient(Patient patient) throws UpdateNotAuthorizedException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails user = (MyUserDetails) auth.getPrincipal();

        if(!patient.getUsername().equals(user.getUsername())) {
            throw new UpdateNotAuthorizedException("You are not authorized to do this update");
        }

        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        patient.setRole("ROLE_USER");
        patientRepository.save(patient);
    }

    @Override
    public Patient getPatientById(int id) {
        Optional<Patient> patient = patientRepository.findById(id);
        return patient.get();
    }

    @Override
    public void deletePatient(int id) {
        patientRepository.deleteById(id);
    }
}
