package com.excilys.model;

public class Company {

	public long id;
	public String name;
	
	public Company() {
	}
	
	public Company(long id, String name) {
		this.id = id;
		this.name=name;
	}

	public String toString() {
		return this.id + " | " + this.name; 
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
