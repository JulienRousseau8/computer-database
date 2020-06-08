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

	@Override
	public String toString() {
		return this.id + " | " + this.name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyDTO other = (CompanyDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
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
