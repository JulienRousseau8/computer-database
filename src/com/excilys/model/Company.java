package com.excilys.model;

public class Company {

	public int id;
	public String name;
	
	public Company() {
	}
	
	public Company(String name) {
		this.name=name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
