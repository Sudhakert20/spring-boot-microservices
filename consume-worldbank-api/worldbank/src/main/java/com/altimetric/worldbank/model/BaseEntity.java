package com.altimetric.worldbank.model;

import java.io.Serializable;

public class BaseEntity implements Serializable{

	private static final long serialVersionUID = -8614809849156758292L;
	
	private String id;
	private String iso2Code;
	private String value;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIso2Code() {
		return iso2Code;
	}
	public void setIso2Code(String iso2Code) {
		this.iso2Code = iso2Code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	

}
