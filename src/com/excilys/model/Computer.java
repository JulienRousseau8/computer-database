package com.excilys.model;

import java.time.LocalDateTime;

public class Computer {

	public long id;
	public String name;
	public LocalDateTime introduced;
	public LocalDateTime discontinued;
	public long company_id;
	
	public Computer() {
	}
	
	public Computer(long id, String name, LocalDateTime introduced, LocalDateTime discontinued, long company_id) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company_id = company_id;
	}

	public String toString() {
		return this.id + " | " + this.name + " | " + this.introduced + " | " + this.discontinued + " | " + this.company_id;
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

	public LocalDateTime getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDateTime introduced) {
		this.introduced = introduced;
	}

	public LocalDateTime getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDateTime discontinued) {
		this.discontinued = discontinued;
	}

	public long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(long company_id) {
		this.company_id = company_id;
	}
	
	
	
}
