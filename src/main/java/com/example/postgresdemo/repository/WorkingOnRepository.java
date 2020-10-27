package com.example.postgresdemo.repository;

import com.example.postgresdemo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.postgresdemo.model.WorkingOn;

import java.util.List;


@Repository
public interface WorkingOnRepository extends JpaRepository<WorkingOn, Integer> {

    @Query(value="SELECT * FROM working_on WHERE working_on.emp_id=?1",
            nativeQuery = true)
    List<WorkingOn> findByEmp_id(int emp_id);

}
