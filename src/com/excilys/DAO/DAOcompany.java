package com.excilys.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.model.Company;
import com.excilys.persistence.MySQLConnect;

public class DAOcompany {

	public static DAOcompany daoCompany;
	
	private static final String getCompanies = "SELECT id,name FROM company";
	private static final String getCompanyById = "SELECT id, name FROM company WHERE id=?";

	private DAOcompany() {
	}
	
	public static DAOcompany getInstance() {
		if(daoCompany == null) {
			daoCompany = new DAOcompany();
		}
		return daoCompany;
	}
	
	public ArrayList<Company> getCompanies() throws SQLException {
		ResultSet allCompaniesRes;
		ArrayList<Company> listCompanies = new ArrayList<Company>();
		
		try(PreparedStatement st = MySQLConnect.conn.prepareStatement(getCompanies)){
			allCompaniesRes = st.executeQuery();
			while(allCompaniesRes.next()) {
				long id = allCompaniesRes.getLong("id");
				String name = allCompaniesRes.getString("name");
				
				Company company = new Company(id, name);
				listCompanies.add(company);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return listCompanies;
	}
	
	public Company getCompanyById(long id) throws SQLException {
		ResultSet CompanyRes;
		try (PreparedStatement st = MySQLConnect.conn.prepareStatement(getCompanyById)){
			st.setLong(1, id);
			CompanyRes = st.executeQuery();
			
			if(CompanyRes.first()) {
				long computerid = CompanyRes.getLong("id");
				String name = CompanyRes.getString("name");;
				
				Company company = new Company(computerid, name); 
				return company;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
