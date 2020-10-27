package com.example.postgresdemo.model;

import com.example.postgresdemo.model.Employee;
import com.example.postgresdemo.model.Skill;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EmployeeWithSkill {                      //Added Bahaa
    private Employee employeedata;
    private Skill skilldata;

    public Employee getEmployeedata() {
        return employeedata;
    }

    public void setEmployeedata(Employee employeedata) {
        this.employeedata = employeedata;
    }

    public Skill getSkilldata() {
        return skilldata;
    }

    public void setSkilldata(Skill skilldata) {
        this.skilldata = skilldata;
    }
}
