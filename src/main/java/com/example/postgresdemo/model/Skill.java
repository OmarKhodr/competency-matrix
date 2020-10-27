package com.example.postgresdemo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
@Table(name = "Skills")
@IdClass(SkillID.class)
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "skill")
public class Skill {

	@Id @ManyToOne @JoinColumn(name="emp_id", referencedColumnName = "emp_id")
    private Employee emp_id;

    @Id
    @Size(max = 45)
    private String skill;

    //Added By Omar
	@Size(max = 45)
	private String category;

    @Min(0)
    @Max(3)
    private float competency;

	public Employee getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Employee emp_id) {
		this.emp_id = emp_id;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public float getCompetency() {
		return competency;
	}

	public void setCompetency(float competency) {
		this.competency = competency;
	}

	public String getCategory() { return category; }

	public void setCategory(String category) { this.category = category; }
}