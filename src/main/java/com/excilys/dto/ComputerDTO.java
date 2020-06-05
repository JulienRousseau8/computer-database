package com.excilys.dto;

public class ComputerDTO {
	public String id;
	public String name;
	public String introduced;
	public String discontinued;
	public String companyId;

	private ComputerDTO() {
	}

	private ComputerDTO(ComputerDTOBuilder computerDTOBuilder) {
		this.id = computerDTOBuilder.id;
		this.name = computerDTOBuilder.name;
		this.introduced = computerDTOBuilder.introduced;
		this.discontinued = computerDTOBuilder.discontinued;
		this.companyId = computerDTOBuilder.companyId;
	}

	public String toString() {
		return this.id + " | " + this.name + " | " + this.introduced + " | " + this.discontinued + " | " + this.companyId;
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

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public static class ComputerDTOBuilder {

		private String id;
		private String name;
		private String introduced;
		private String discontinued;
		private String companyId;

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

		public ComputerDTOBuilder setCompanyId(String companyId) {
			this.companyId = companyId;
			return this;
		}

	}
}
