package com.excilys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.DAO.DAOcompany;
import com.excilys.model.Company;

public class CompanyService {

	private static Logger logger = LoggerFactory.getLogger(CompanyService.class);
	
	public Optional<Company> getCompanyById(String companyID) throws SQLException {
		try {
			long compId = Long.parseLong(companyID);
			Optional<Company> company = DAOcompany.getInstance().getCompanyById(compId);
			if (!company.isPresent()) {
				logger.info("Aucune entreprise ne correspond à cet ID");
			}
			return company;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public ArrayList<Company> getAllCompanies() throws SQLException {
		return DAOcompany.getInstance().getCompanies();
	}
}
