package com.excilys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.DAO.DAOcompany;
import com.excilys.DAO.DAOcomputer;
import com.excilys.model.Company;
import com.excilys.model.Computer;

public class CompanyService {

	private static Logger logger = LoggerFactory.getLogger(CompanyService.class);
	ComputerService computerService;
	
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

	public void deleteCompany(int id) {
		Optional<Company> company = DAOcompany.getInstance().getCompanyById(id);
		List<Computer> listComputerToDelete = new ArrayList<Computer>();
		listComputerToDelete = DAOcomputer.getInstance().getComputersByCompanyId(id);
		if (company.isPresent()) {
			for(Computer c : listComputerToDelete) {
				DAOcomputer.getInstance().deleteComputer(c.getId());
			}
			DAOcompany.getInstance().deleteCompany(id);
			logger.info(listComputerToDelete.size() + " ordinateurs supprimés");
			logger.info(company.get().toString() + ": Entreprise supprimé");
		} else {
			logger.info("Aucune entreprise ne correspond à cet ID");
		}
	}
}
