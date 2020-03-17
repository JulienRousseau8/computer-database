package com.excilys.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.excilys.service.ConvertDate;
import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.model.*;
import com.excilys.service.*;
import com.excilys.service.Validators;

public class ActionsMenu {

	private static Logger logger = LoggerFactory.getLogger(ActionsMenu.class);
    
	private Scanner scan = new Scanner(System.in);
	public static ActionsMenu actionMenu;
	ComputerService computerService = new ComputerService();
	CompanyService companyService = new CompanyService();

	private ActionsMenu() {
	}

	public static ActionsMenu getInstance() {
		if(actionMenu == null) {
			actionMenu = new ActionsMenu();
		}
		return actionMenu;
	}

	public void showDetails() throws SQLException{
		System.out.println("Entrer un ID");
		int detailId = scan.nextInt();
		computerService.getComputerById(detailId);
	}

	public void createComputer() throws SQLException {
		Computer computer = new Computer.ComputerBuilder().build();

		System.out.println("Entrer un nom");
		String computerName = scan.nextLine();
		if(computerName.isEmpty()) {
			logger.info("Nom requis");
		}else {
			computer.setName(computerName);

			System.out.println("Date d'introduction (yyyy-MM-dd):");
			String dateIntro = scan.nextLine();
			computer.setIntroduced(ConvertDate.convert(dateIntro));

			System.out.println("Date d'arret : (yyyy-MM-dd)");
			String dateArret = scan.nextLine();
			boolean ordreDate = Validators.verifierDateOrdre(dateIntro, dateArret);
			if(ordreDate) {
				computer.setDiscontinued(ConvertDate.convert(dateArret));

				System.out.println("ID de l'entreprise");
				String companyId = scan.nextLine();
				if(companyId.isEmpty()) {
					logger.info("Id de la compagnie requis");
				}
				else {
					long intCompanyID = Long.parseLong(companyId);
					Optional<Company> optionalCompany = companyService.getCompanyById(intCompanyID);
					if(optionalCompany.isPresent()){	
						Company company = optionalCompany.get();
						computer.setCompany(company);
					}else 
						logger.info("Company ID not found!");
					computerService.createComputer(computer);
				}
			} else logger.info("Date non valide !");
		}
	}
	
	public void createDTOcomputer() {
				
		System.out.println("Entrer un nom");
		String computerName = scan.nextLine();
		
		System.out.println("Date d'introduction (yyyy-MM-dd):");
		String dateIntro = scan.nextLine();
		
		System.out.println("Date d'arret : (yyyy-MM-dd)");
		String dateArret = scan.nextLine();
		
		System.out.println("ID de l'entreprise");
		String companyId = scan.nextLine();
		CompanyDTO companyDTO = new CompanyDTO.CompanyDTOBuilder().setId(companyId).build();
		ComputerDTO computer = new ComputerDTO.ComputerDTOBuilder().setName(computerName).setIntroduced(dateIntro).setDiscontinued(dateArret).setCompany(companyDTO).build();
		
	}

	public void updateComputer() throws SQLException{
		System.out.println("Entrer un ID a modifier");
		int majId = scan.nextInt();
		scan.nextLine();
		Optional<Computer> optionalComputer = computerService.getComputerById(majId);
		System.out.println(optionalComputer.toString());
		if(optionalComputer.isPresent()){
			Computer oldComputer = optionalComputer.get();
			Computer newComputer = new Computer.ComputerBuilder().setId(majId).build();

			System.out.println("nouveau nom :");
			String eName = scan.nextLine();
			if(eName.isEmpty()) {
				newComputer.name = oldComputer.name;
			}else {
				newComputer.setName(eName);
			}

			System.out.println("nouvelle date d'introduction : (yyyy-MM-dd)");
			String eIntroduced = scan.nextLine();
			if(eIntroduced.isEmpty()) {
				newComputer.introduced = oldComputer.introduced;
			}else {
				newComputer.setIntroduced(ConvertDate.convert(eIntroduced));
			}

			System.out.println("Date d'arret : (yyyy-MM-dd)");
			String eDiscontinued = scan.nextLine();
			if(eDiscontinued.isEmpty()) {
				newComputer.discontinued = oldComputer.discontinued;
			}else {
				newComputer.setDiscontinued(ConvertDate.convert(eDiscontinued));
			}

			System.out.println("modifier ID de l'entreprise : ");
			String EcompanyId = scan.nextLine();
			long company_id = Long.parseLong(EcompanyId);
			Optional<Company> optionalCompany = companyService.getCompanyById(company_id);
			if(optionalCompany.isPresent()){	
				Company company = optionalCompany.get();
				if(EcompanyId.isEmpty()) {
					oldComputer.company = oldComputer.company;
					computerService.updateComputer(newComputer);
					logger.info("Ordinateur modifié :");
					logger.info(newComputer.toString());
				}
				else {
					newComputer.setCompany(company);
					computerService.updateComputer(newComputer);
					logger.info("Ordinateur modifié :");
					logger.info(newComputer.toString());
				}
			}else 
				logger.info("Company not found!");
		}
	}

	public void deleteComputer() throws SQLException {
		System.out.println("Entrer un ID");
		int suppId = scan.nextInt();
		computerService.deleteComputer(suppId);
	}

	public void pagination() {
		Pagination page = new Pagination(computerService.countAllComputer());
		ArrayList<Computer> computerPage = new ArrayList<Computer>();
		computerPage = computerService.getPageComputer(page);
		page.displayPageContent(computerPage);

		boolean quit = true;
		while(quit) {
			System.out.println("prev page : p | page " + page.getPageNum() + "/" + page.getPageMax() + " | next page : n | quit q");
			String input = scan.nextLine();
			switch(input) {
			case "p" :
				page.prevPage();
				computerPage = computerService.getPageComputer(page);
				page.displayPageContent(computerPage);
				break;
			case "n" :
				page.nextPage();
				computerPage = computerService.getPageComputer(page);
				page.displayPageContent(computerPage);
				break;
			case "q" :
				quit = false;
				break;
			default :
				logger.info("Entrée incorecte");
			}
		}
	}

}
