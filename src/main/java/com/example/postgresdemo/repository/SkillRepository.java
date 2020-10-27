package com.example.postgresdemo.repository;

import com.example.postgresdemo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.postgresdemo.model.Skill;

import java.util.List;


@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {

    @Query(value="SELECT * FROM skills WHERE skills.emp_id=?1 AND skills.skill=?2 AND skills.category=?3",     //Added Bahaa & Omar
            nativeQuery = true)
    Skill findSkillBySkillNameAndEmpIdAndCategory(int emp_id ,String skill, String category);

    @Query(value="SELECT * FROM skills WHERE skills.emp_id=?1 AND skills.skill=?2",     //Added Bahaa
            nativeQuery = true)
    Skill findSkillBySkillNameAndEmpId(int emp_id ,String skill);

    //Added by Omar
    @Query(value="SELECT * FROM skills WHERE skills.skill=?1 AND skills.category=?2",
            nativeQuery = true)
    List<Skill> findSkillsBySkillNameAndCategory(String skill, String category);

    //Added by Omar
    @Query(value="SELECT * FROM skills WHERE skills.category=?1",
            nativeQuery = true)
    List<Skill> findSkillsByCategory(String category);


}