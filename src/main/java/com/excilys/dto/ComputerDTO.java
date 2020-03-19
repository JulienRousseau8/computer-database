package com.excilys.dto;

public class ComputerDTO {
	public String id;
	public String name;
	public String introduced;
	public String discontinued;
	public CompanyDTO company;

	private ComputerDTO() {
	}

	private ComputerDTO(ComputerDTOBuilder computerDTOBuilder) {
		this.id = computerDTOBuilder.id;
		this.name = computerDTOBuilder.name;
		this.introduced = computerDTOBuilder.introduced;
		this.discontinued = computerDTOBuilder.discontinued;
		this.company = computerDTOBuilder.company;
	}

	public String toString() {
		return this.id + " | " + this.name + " | " + this.introduced + " | " + this.discontinued + " | " + this.company;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public CompanyDTO getCompany() {
		return company;
	}

	public void setCompany(CompanyDTO company) {
		this.company = company;
	}

	public static class ComputerDTOBuilder {

		private String id;
		private String name;
		private String introduced;
		private String discontinued;
		private CompanyDTO company;

		public ComputerDTO build() {
			return new ComputerDTO(this);
		}

		public ComputerDTOBuilder setId(String id) {
			this.id = id;
			return this;
		}

		public ComputerDTOBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public ComputerDTOBuilder setIntroduced(String introduced) {
			this.introduced = introduced;
			return this;
		}

		public ComputerDTOBuilder setDiscontinued(String discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public ComputerDTOBuilder setCompany(CompanyDTO company) {
			this.company = company;
			return this;
		}

	}
}
