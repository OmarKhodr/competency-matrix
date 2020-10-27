package com.example.postgresdemo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class WorkingOnID implements Serializable {

    public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	private int emp_id;
    private int project_id;

    public WorkingOnID(int emp_id, int project_id) {
        this.emp_id = emp_id;
        this.project_id = project_id;
    }
}
