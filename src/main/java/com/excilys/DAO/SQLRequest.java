package com.excilys.DAO;

public enum SQLRequest {
	GETCOMPUTERS("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
			+ "FROM computer LEFT JOIN company ON company.id = company_id"),
	GETCOMPUTERBYID ("SELECT computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id, company.name "
			+ "FROM computer " + "LEFT JOIN company " + "ON company.id = company_id " + "WHERE computer.id=?"),
	CREATECOMPUTER ("INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)"),
	UPDATECOMPUTER ("UPDATE computer SET  name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE Id = ?"),
	DELETECOMPUTER ("DELETE FROM computer WHERE id=?"),
	COUNTCOMPUTERS ("SELECT COUNT(id) AS rowcount FROM computer"),
	GETPAGECOMPUTERS ("SELECT computer.name, computer.id, computer.introduced, computer.discontinued, computer.company_id, company.name "
			+ "FROM computer AS computer " + "LEFT JOIN company AS company " + "ON company.id = computer.company_id "
			+ "LIMIT ?, ?"),
	SEARCHCOMPUTERPAGE ("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
			+ "FROM computer LEFT JOIN company ON company.id = company_id "
			+ "WHERE computer.name LIKE ? ORDER BY computer.name LIMIT ?, ?  "),
	SEARCHCOMPUTER ("SELECT computer.id, computer.name, computer.introduced, computer.discontinued, computer.company_id, company.name "
			+ "FROM computer LEFT JOIN company ON company.id = company_id "
			+ "WHERE computer.name LIKE ?"),
	GETPAGECOMPUTERORDERBYNAME ("SELECT computer.id, computer.name, computer.introduced , computer.discontinued , company_id, company.name "
			+ "FROM computer LEFT JOIN company ON company_id = company.id ORDER BY computer.name LIMIT ?,?;"),
	
	GETCOMPANIES ("SELECT company.id, company.name FROM company"),
	GETCOMPANYBYID ("SELECT id, name FROM company WHERE id=?");
	
	private String requete;

	SQLRequest(String query) {
		this.requete = query;
	}

	public String getQuery() {
		return this.requete;
	}
}
