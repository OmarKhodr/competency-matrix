package com.example.postgresdemo.model;

import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class SkillID implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	private int emp_id;

    @Size(max = 45)
    private String skill;

    public SkillID() {}

    public SkillID(int emp_id, String skill) {
        this.emp_id = emp_id;
        this.skill = skill;
    }
}
