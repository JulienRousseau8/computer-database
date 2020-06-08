package com.excilys.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.DAO.DAOcompany;
import com.excilys.model.Company;

public class CompanyService {

	private static Logger logger = LoggerFactory.getLogger(CompanyService.class);
	
	public Optional<Company> getCompanyById(String companyID){
		try {
			long compId = Long.parseLong(companyID);
			Optional<Company> company = DAOcompany.getInstance().getCompanyById(compId);
			if (!company.isPresent()) {
				logger.info("Aucune entreprise ne correspond à cet ID");
			}
			return company;
		} catch (NumberFormatException NFexception) {
			NFexception.printStackTrace();
		} 
		return Optional.empty();
	}

	public List<Company> getAllCompanies(){
		return DAOcompany.getInstance().getCompanies();
	}
}
