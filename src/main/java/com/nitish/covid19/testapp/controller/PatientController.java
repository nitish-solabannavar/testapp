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
    @Secured ({"ROLE_ADMIN"})
    public String hello(){
        return "<h2>Hello World! How you doing?</h2>";
    }

    @GetMapping("/get")
    @Secured ({"ROLE_ADMIN"})
    public List<Patient> getPatients(){
        return patientService.getPatient();
    }

    @GetMapping("/get/{patientId}")
    @Secured ({"ROLE_ADMIN"})
    public Patient getPatientById(@PathVariable int patientId){
        return patientService.getPatientById(patientId);
    }

    @PostMapping("/register")
    public void savePatient(@Valid @RequestBody Patient patient){
        try {
            patientService.createPatient(patient);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @PutMapping("/update")
    public void updatePatient(@Valid @RequestBody Patient patient){
        try {
            patientService.updatePatient(patient);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @DeleteMapping("/delete/{patientId}")
    @Secured ({"ROLE_ADMIN"})
    public void deletePatient(@PathVariable int patientId){
        patientService.deletePatient(patientId);
    }
}
