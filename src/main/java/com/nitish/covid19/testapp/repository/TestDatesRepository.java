package com.nitish.covid19.testapp.repository;

import com.nitish.covid19.testapp.pojo.TestDates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface TestDatesRepository extends JpaRepository<TestDates, Integer> {
    Optional<TestDates> findByDate(LocalDate date);
}
