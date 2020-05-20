package com.nitish.covid19.testapp.security;

import com.nitish.covid19.testapp.pojo.Patient;
import com.nitish.covid19.testapp.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyDetailsUserService implements UserDetailsService {

    @Autowired
    PatientRepository patientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Patient> patient = patientRepository.findByUsername(username);

        return new MyUserDetails(patient.get());
    }
}
