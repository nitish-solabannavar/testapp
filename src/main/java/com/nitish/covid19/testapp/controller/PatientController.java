package com.nitish.covid19.testapp.controller;

import com.nitish.covid19.testapp.pojo.Patient;
import com.nitish.covid19.testapp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/testapp")
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping("/")
    public String hello(){
        return "<h2>Hello World!</h2>";
    }

    @GetMapping("/patient")
    public List<Patient> getPatients(){
        return patientService.getPatient();
    }

    @GetMapping("/patient/{patientId}")
    public Patient getPatientById(@PathVariable int patientId){
        return patientService.getPatientById(patientId);
    }

    //@PermitAll
    @PostMapping("/patient")
    public void savePatient(@RequestBody Patient patient){
        try {
            patientService.createPatient(patient);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @PutMapping("/patient")
    public void updatePatient(@RequestBody Patient patient){
        try {
            patientService.updatePatient(patient);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @DeleteMapping("/patient/{patientId}")
    public void deletePatient(@PathVariable int patientId){
        patientService.deletePatient(patientId);
    }
}
