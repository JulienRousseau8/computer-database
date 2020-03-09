package com.excilys.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.model.Company;
import com.excilys.persistence.MySQLConnect;

public class DAOcompany {

	MySQLConnect mysql = MySQLConnect.getDbCon();
	private static final String getCompanies = "SELECT id,name FROM company";
	
	public ArrayList<Company> getCompanies() throws SQLException {
		ArrayList<Company> listCompanies = new ArrayList<Company>();
		ResultSet companyRes = mysql.query(getCompanies);
		while(companyRes.next()) {
			long id = companyRes.getLong("id");
			String name = companyRes.getString("name");
			Company company = new Company(id, name);
			listCompanies.add(company);
		}
		return listCompanies;
	}
	
}
