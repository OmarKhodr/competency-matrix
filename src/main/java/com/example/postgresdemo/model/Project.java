package com.example.postgresdemo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "Projects")
public class Project {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int project_id;

    @Size(max = 45)
    private String project_name;

    @ManyToOne @JoinColumn(name="project_leader", referencedColumnName = "emp_id")
    private Employee project_leader;

    private short completed;

    @Column(columnDefinition = "DATE")
    private LocalDate start_date;

    @Column(columnDefinition = "DATE")
    private LocalDate end_date;

    @Column(columnDefinition = "DATE")
    private LocalDate deadline;

    private int severity;

    @Min(0)
    @Max(3)
    private float efficiency;

    @Min(0)
    @Max(3)
    private float effectiveness;

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public Employee getProject_leader() {
		return project_leader;
	}

	public void setProject_leader(Employee project_leader) {
		this.project_leader = project_leader;
	}

	public short getCompleted() {
		return completed;
	}

	public void setCompleted(short completed) {
		this.completed = completed;
	}

	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public LocalDate getEnd_date() {
		return end_date;
	}

	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
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

}
