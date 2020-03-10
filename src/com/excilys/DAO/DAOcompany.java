package com.excilys.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.model.Company;
import com.excilys.persistence.MySQLConnect;

public class DAOcompany {

	MySQLConnect mysql = MySQLConnect.getDbCon();
	private static final String getCompanies = "SELECT id,name FROM company";
	private static final String getCompanyById = "SELECT id, name FROM company WHERE id=?";
	private final static String createCompany = "INSERT INTO company (name) VALUES (?)";

	public ArrayList<Company> getCompanies() throws SQLException {
		ResultSet allCompaniesRes;
		ArrayList<Company> listCompanies = new ArrayList<Company>();
		
		PreparedStatement st = MySQLConnect.conn.prepareStatement(getCompanies);
		allCompaniesRes = st.executeQuery();
		
		while(allCompaniesRes.next()) {
			long id = allCompaniesRes.getLong("id");
			String name = allCompaniesRes.getString("name");
			
			Company company = new Company(id, name);
			listCompanies.add(company);
		}
		return listCompanies;
	}
	
	public Company getCompanyById(int id) throws SQLException {
		ResultSet CompanyRes;
		
		PreparedStatement st = MySQLConnect.conn.prepareStatement(getCompanyById);
		st.setLong(1, id);
		CompanyRes = st.executeQuery();
		
		while(CompanyRes.next()) {
			long computerid = CompanyRes.getLong("id");
			String name = CompanyRes.getString("name");;
			
			Company company = new Company(computerid, name); 
			return company;
		}
		return null;
	}
	
	public void createCompany(Company company) throws SQLException {
		PreparedStatement st = MySQLConnect.conn.prepareStatement(createCompany);
		st.setString(1, company.getName());
		st.executeUpdate();
	}
	
}
