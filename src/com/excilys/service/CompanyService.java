package com.excilys.service;

import java.sql.SQLException;
import java.util.Optional;

import com.excilys.DAO.DAOcompany;
import com.excilys.model.Company;

public class CompanyService {

	public Optional<Company> getCompanyById(long intCompanyID) throws SQLException {
		Optional<Company> optionalCompany = DAOcompany.getInstance().getCompanyById(intCompanyID);
		return optionalCompany;
	}
}
