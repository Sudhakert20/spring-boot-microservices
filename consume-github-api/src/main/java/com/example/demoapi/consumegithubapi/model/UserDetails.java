package com.example.demoapi.consumegithubapi.model;

import java.io.Serializable;


public class UserDetails implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String company;
	private String location;
	
	public UserDetails() {
		super();
	}
	
	public UserDetails(String name, String company, String location) {
		super();
		this.name = name;
		this.company = company;
		this.location = location;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "UserDetails [name=" + name + ", company=" + company + ", location=" + location + "]";
	}
	
	
}
