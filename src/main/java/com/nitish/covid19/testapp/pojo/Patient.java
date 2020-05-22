package com.nitish.covid19.testapp.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "First Name cannot be null")
    @NotBlank
    private String firstName;

    @NotNull(message = "Last Name cannot be null")
    @NotBlank
    private String lastName;

    @NotNull(message = "Email cannot be null")
    @NotBlank
    @Pattern(regexp = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",message="Please provide a valid email address")
    private String username;

    @NotNull(message = "Age cannot be null")
    @Min(0)
    private int age;

    @JsonIgnore
    @NotNull(message = "Password cannot be null")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}",message="Password must contain min 8 characters, a uppercase letter, a lowercase letter and a special character")
    private String password;

    @JsonIgnore
    private String role;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    private List<Test> test;

    public Patient(){ }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Test> getTest() {
        return test;
    }

    //public void setTest(List<Test> test) {
      //  this.test = test;
   // }

    public void addTest(Test t){
        if(this.test == null){
            this.test = new ArrayList<>();
        }

        this.test.add(t);
        t.setPatient(this);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
