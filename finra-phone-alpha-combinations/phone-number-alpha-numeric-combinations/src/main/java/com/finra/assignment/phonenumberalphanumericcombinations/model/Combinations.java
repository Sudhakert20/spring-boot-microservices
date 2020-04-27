package com.finra.assignment.phonenumberalphanumericcombinations.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="combinations")
public class Combinations {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long phoneNumber;
	private String combination;

	public Combinations() {
		super();
	}

	public Combinations(Long phoneNumber, String combination) {
		super();
		this.phoneNumber = phoneNumber;
		this.combination = combination;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCombination() {
		return combination;
	}

	public void setCombination(String combination) {
		this.combination = combination;
	}
	
	
}
