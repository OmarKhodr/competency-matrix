package com.example.postgresdemo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Data
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "branch_id")
@Table(name="branches")
public class Branch {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int branch_id;

    @Size(max = 45)
    private String address;

    @OneToOne(cascade = CascadeType.ALL) @JoinColumn(name = "branch_manager", referencedColumnName = "emp_id")
    private Employee branch_manager;

    @Size(max = 45)
    private String country;

	public int getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Employee getBranch_manager() {
		return branch_manager;
	}

	public void setBranch_manager(Employee branch_manager) {
		this.branch_manager = branch_manager;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
    
}

