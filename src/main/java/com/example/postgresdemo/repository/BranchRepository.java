package com.example.postgresdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.postgresdemo.model.Branch;


@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {
}
