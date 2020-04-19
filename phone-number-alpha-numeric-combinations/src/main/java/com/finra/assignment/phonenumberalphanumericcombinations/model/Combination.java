package com.finra.assignment.phonenumberalphanumericcombinations.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="combinations")
public class Combination {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String value;

	public Combination() {
		super();
	}

	public Combination(Long id, String value) {
		super();
		this.id = id;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
