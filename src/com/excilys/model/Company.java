package com.excilys.model;

public class Company {

	public long id;
	public String name;

	public Company() {
	}

	public Company(CompanyBuilder companyBuilder) {
		this.id = companyBuilder.id;
		this.name= companyBuilder.name;
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

	public static class CompanyBuilder {
		public long id;
		public String name;
		
		public Company build() {
			return new Company(this);
		}
		
		public CompanyBuilder setId(long id) {
			this.id = id;
			return this;
		}
		public CompanyBuilder setName(String name) {
			this.name = name;
			return this;
		}
		
	}

}
