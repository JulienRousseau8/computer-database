package com.excilys.model;

import java.time.LocalDate;

public class Computer {

	public long id;
	public String name;
	public LocalDate introduced;
	public LocalDate discontinued;
	public Company company;

	private Computer() {
	}

	private Computer(ComputerBuilder computerBuilder) {
		this.id = computerBuilder.id;
		this.name = computerBuilder.name;
		this.introduced = computerBuilder.introduced;
		this.discontinued = computerBuilder.discontinued;
		this.company = computerBuilder.company;
	}

	public String toString() {
		return this.id + " | " + this.name + " | " + this.introduced + " | " + this.discontinued + " | " + this.company;
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

	public LocalDate getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public static class ComputerBuilder {
		
		private long id;
		private String name;
		private LocalDate introduced;
		private LocalDate discontinued;
		private Company company;
		
		public Computer build() {
			return new Computer(this);
		}
		
		public ComputerBuilder setId(long id) {
			this.id = id;
			return this;
		}
		public ComputerBuilder setName(String name) {
			this.name = name;
			return this;
		}
		public ComputerBuilder setIntroduced(LocalDate introduced) {
			this.introduced = introduced;
			return this;
		}
		public ComputerBuilder setDiscontinued(LocalDate discontinued) {
			this.discontinued = discontinued;
			return this;
		}
		public ComputerBuilder setCompany(Company company) {
			this.company = company;
			return this;
		}
		
		
	}
}
