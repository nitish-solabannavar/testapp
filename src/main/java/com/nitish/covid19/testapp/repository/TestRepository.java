package com.nitish.covid19.testapp.repository;

import com.nitish.covid19.testapp.pojo.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Integer> {
}
