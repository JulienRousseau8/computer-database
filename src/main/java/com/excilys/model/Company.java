package com.excilys.model;

public class Company {

	public Long id;
	public String name;

	private Company() {
	}

	private Company(CompanyBuilder companyBuilder) {
		this.id = companyBuilder.id;
		this.name = companyBuilder.name;
	}

	public String toString() {
		return this.id + " | " + this.name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static class CompanyBuilder {
		public Long id;
		public String name;

		public Company build() {
			return new Company(this);
		}

		public CompanyBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public CompanyBuilder setName(String name) {
			this.name = name;
			return this;
		}

	}

}
