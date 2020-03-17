package com.excilys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import com.excilys.DAO.DAOcompany;
import com.excilys.model.Company;

public class CompanyService {

	public Optional<Company> getCompanyById(long intCompanyID) throws SQLException {
		return DAOcompany.getInstance().getCompanyById(intCompanyID);
	}
	
	public ArrayList<Company> getAllCompanies() throws SQLException{
		return DAOcompany.getInstance().getCompanies();
	}
}
