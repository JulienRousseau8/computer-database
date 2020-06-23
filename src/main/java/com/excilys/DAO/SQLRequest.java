package com.excilys.DAO;

public enum SQLRequest {
	GETCOMPUTERS("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
			+ "FROM computer LEFT JOIN company ON company.id = company_id"),
	GETCOMPUTERBYID ("SELECT computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id, company.name "
			+ "FROM computer " + "LEFT JOIN company " + "ON company.id = company_id " + "WHERE computer.id=:id"),
	CREATECOMPUTER ("INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (:name, :introduced, :discontinued, :company.id)"),
	UPDATECOMPUTER ("UPDATE computer SET  name = :name, introduced = :introduced, discontinued = :discontinued, company_id = :company.id WHERE Id = :computer.id"),
	DELETECOMPUTER ("DELETE FROM computer WHERE id=:id"),
	COUNTCOMPUTERS ("SELECT COUNT(id) AS rowcount FROM computer"),
	GETPAGECOMPUTERS ("SELECT computer.name, computer.id, computer.introduced, computer.discontinued, computer.company_id, company.name "
			+ "FROM computer AS computer " + "LEFT JOIN company AS company " + "ON company.id = computer.company_id "
			+ "LIMIT :1,:2"),
	SEARCHCOMPUTERPAGE ("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
			+ "FROM computer LEFT JOIN company ON company.id = company_id "
			+ "WHERE company.name LIKE :search OR computer.name LIKE :search "
			+ "LIMIT :1, :2 "),
	SEARCHCOMPUTER ("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
			+ "FROM computer LEFT JOIN company ON company.id = company_id "
			+ "WHERE company.name LIKE :search OR computer.name LIKE :search "),
	
	GETCOMPUTERSBYCOMPANYID ("SELECT computer.id, computer.name, computer.introduced , computer.discontinued , company_id, company.name"
			+ " FROM computer LEFT JOIN company ON company.id = company_id WHERE company.id = :id"),
	GETCOMPANIES ("SELECT company.id, company.name FROM company"),
	GETCOMPANYBYID ("SELECT company.id, company.name FROM company WHERE company.id= :id"),
	DELETECOMPANY ("DELETE FROM company WHERE id=:id");
	
	private String requete;

	SQLRequest(String query) {
		this.requete = query;
	}

	public String getQuery() {
		return this.requete;
	}
}