package com.example.postgresdemo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Data
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "emp_id")
@Table(name = "employees")
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int emp_id;

    @Size(max = 45)
    private String first_name;

	@Size(max = 45)
    private String last_name;

    @Size(max = 45)
    private String position;

    @Size(max = 45)
    private String department;

    @Size(max = 1)
    private String gender;

    @Column(columnDefinition = "DATE")
    private LocalDate hire_date;

    @ManyToOne @JoinColumn(name="branch_id", referencedColumnName = "branch_id")
    private Branch branch_id;

    @ManyToOne @JoinColumn(name="reports_to", referencedColumnName = "emp_id")
    private Employee reports_to;

//    @OneToMany(cascade = {CascadeType.ALL})
//    private List<Skill> skills;

    @Size(max = 45)
    private String phone_num;

    @Min(0)
    @Max(3)
    private float efficiency;

    @Min(0)
    @Max(3)
    private float effectiveness;

    @Size(max = 45)
    private String nationality;

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getHire_date() {
		return hire_date;
	}

	public void setHire_date(LocalDate hire_date) {
		this.hire_date = hire_date;
	}

	public Branch getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(Branch branch_id) {
		this.branch_id = branch_id;
	}

	public Employee getReports_to() {
		return reports_to;
	}

	public void setReports_to(Employee reports_to) {
		this.reports_to = reports_to;
	}

	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}

	public float getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(float efficiency) {
		this.efficiency = efficiency;
	}

	public float getEffectiveness() {
		return effectiveness;
	}

	public void setEffectiveness(float effectiveness) {
		this.effectiveness = effectiveness;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
    
}
