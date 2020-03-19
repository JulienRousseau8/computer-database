package com.excilys.dto;

public class CompanyDTO {

	public String id;
	public String name;

	private CompanyDTO() {
	}

	private CompanyDTO(CompanyDTOBuilder companyDTOBuilder) {
		this.id = companyDTOBuilder.id;
		this.name = companyDTOBuilder.name;
	}

	public String toString() {
		return this.id + " | " + this.name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static class CompanyDTOBuilder {
		public String id;
		public String name;

		public CompanyDTO build() {
			return new CompanyDTO(this);
		}

		public CompanyDTOBuilder setId(String id) {
			this.id = id;
			return this;
		}

		public CompanyDTOBuilder setName(String name) {
			this.name = name;
			return this;
		}

	}

}
