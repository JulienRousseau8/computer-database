package com.excilys.dto;

public class MyUserDTO {
	private String id;
	private String username;
	private String password;
	private String role;
	
	private MyUserDTO() {
	}
	
	private MyUserDTO(Builder userDTOBuilder) {
		this.id = userDTOBuilder.id;
		this.username = userDTOBuilder.username;
		this.password = userDTOBuilder.password;
		this.role = userDTOBuilder.role;
	}	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
		        + password + ", role=" + role + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
		        + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result
		        + ((username == null) ? 0 : username.hashCode());
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
		MyUserDTO other = (MyUserDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}


	public static class Builder{
		private String id;
		private String username;
		private String password;
		private String role;
		
		public MyUserDTO build() {
			return new MyUserDTO(this);
		}
		
		public Builder setId(String id) {
			this.id = id;
			return this;
		}

		public Builder setUsername(String username) {
			this.username = username;
			return this;
		}
		
		public Builder setPassword(String password) {
			this.password = password;
			return this;
		}
		
		public Builder setRole(String role) {
			this.role = role;
			return this;
		}
		
	}
}








