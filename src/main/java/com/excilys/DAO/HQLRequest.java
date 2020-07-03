package com.excilys.DAO;

public enum HQLRequest {

	GETCOMPUTERS("select computer from Computer computer LEFT JOIN computer.company"),	
	GETCOMPUTERBYID ("select computer from Computer computer LEFT JOIN computer.company where computer.id = :id"),
	GETCOMPUTERSBYCOMPANYID("select computer from Computer computer LEFT JOIN computer.company where computer.company_id = :id"),
	UPDATECOMPUTER ("update Computer SET  name = :name, introduced = :introduced, discontinued = :discontinued, company_id = :companyId where id = :id"),
	DELETECOMPUTER ("delete from Computer computer where id=:id"),
	COUNTCOMPUTERS ("select count(c.id) from Computer c"),
	SEARCHCOMPUTER ("select computer from Computer computer LEFT JOIN computer.company where computer.name LIKE :search OR computer.company.name LIKE :search"),
	GETPAGECOMPUTERORDERBYNAME("select computer from Computer computer LEFT JOIN Company company ON computer.company = company.id order by "),
	
	GETCOMPANIES("select company from Company company"),
	GETCOMPANYBYID("select company from Company company where company.id = :id"),
	DELETECOMPANY ("delete from Company company where id=:id");
	
	private String requete;

	HQLRequest(String query) {
		this.requete = query;
	}

	public String getQuery() {
		return this.requete;
	}
}
