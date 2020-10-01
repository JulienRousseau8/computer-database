package com.excilys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.DAO.DAOcomputer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.DAO.DAOcompany;
import com.excilys.model.Company;
import com.excilys.model.Computer;

@Service
public class CompanyService {

	private static Logger logger = LoggerFactory.getLogger(CompanyService.class);
	@Autowired
	private DAOcompany daoCompany;
	@Autowired
	private DAOcomputer daOcomputer;

//	public CompanyService(DAOcompany daoCompany, ComputerService computerService) {
//		this.daoCompany = daoCompany;
//		this.computerService = computerService;
//	}

	public Optional<Company> getCompanyById(String companyID){
		try {
			long compId = Long.parseLong(companyID);
			Optional<Company> company = daoCompany.getCompanyById(compId);
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
		return daoCompany.getCompanies();
	}

	public void deleteCompany(int id) {
		Optional<Company> company = daoCompany.getCompanyById(id);
		List<Computer> listComputerToDelete = new ArrayList<Computer>();
		listComputerToDelete = daOcomputer.getComputersByCompanyId(id);
		if (company.isPresent()) {
			for(Computer c : listComputerToDelete) {
				daOcomputer.deleteComputer(c.getId());
			}
			daoCompany.deleteCompany(id);
			logger.info(listComputerToDelete.size() + " ordinateurs supprimés");
			logger.info(company.get().getName().toString() + ": Entreprise supprimé");
		} else {
			logger.info("Aucune entreprise ne correspond à cet ID");
		}
	}
}
