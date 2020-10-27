package com.example.postgresdemo.model;

import com.example.postgresdemo.model.Employee;
import com.example.postgresdemo.model.Skill;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EmployeeWithSkills {       //Added Bahaa
    private Employee Employeedata;
    private Skill skills[];

    public Employee getEmployeedata() {
        return Employeedata;
    }

    public void setEmployeedata(Employee employeedata) {
        Employeedata = employeedata;
    }

    public Skill[] getSkills() {
        return skills;
    }

    public void setSkills(Skill[] skills) {
        this.skills = skills;
    }
}
