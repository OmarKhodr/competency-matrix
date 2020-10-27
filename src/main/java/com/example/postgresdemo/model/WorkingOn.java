package com.example.postgresdemo.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "working_on")
@IdClass(WorkingOnID.class)
public class WorkingOn {

    @Id @ManyToOne @JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
    private Employee emp_id;

	@Id @ManyToOne @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    private Project project_id;

    @Size(max = 45)
    private String role;

	public Employee getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Employee emp_id) {
		this.emp_id = emp_id;
	}

	public Project getProject_id() {
		return project_id;
	}

	public void setProject_id(Project project_id) {
		this.project_id = project_id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
