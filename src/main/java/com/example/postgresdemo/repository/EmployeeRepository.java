package com.example.postgresdemo.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.postgresdemo.model.Employee;

import java.util.List;

@Repository

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

//    @Query("SELECT a FROM Employee a WHERE a.first_name = ?1 AND a.position = ?2")
//    List<Employee> findByfirstnameAndPosition(String first_name, String position);

    @Query(value="SELECT * FROM employees INNER JOIN skills ON employees.emp_id = skills.emp_id WHERE skills.skill=?1 AND skills.competency=?2",
            nativeQuery = true)
    List<Employee> findBySkillandCompetency(String skill, float competency);



}
