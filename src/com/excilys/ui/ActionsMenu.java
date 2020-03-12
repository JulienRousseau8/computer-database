package com.excilys.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import com.excilys.DAO.*;
import com.excilys.mapper.ConvertDate;
import com.excilys.model.*;

public class ActionsMenu {

	private Scanner scan = new Scanner(System.in);
	public static ActionsMenu actionMenu;

	private ActionsMenu() {
	}

	public static ActionsMenu getInstance() {
		if(actionMenu == null) {
			actionMenu = new ActionsMenu();
		}
		return actionMenu;
	}

	public void showDetails() throws SQLException {
		System.out.println("Entrer un ID");
		int detailId = scan.nextInt();
		Optional<Computer> computer = DAOcomputer.getInstance().getComputerById(detailId);
		System.out.println(computer.toString());
	}

	public void createComputer() throws SQLException {
		Computer computer = new Computer();

		System.out.println("Entrer un nom");
		computer.setName(scan.nextLine());

		System.out.println("Date d'introduction (yyyy-MM-dd):");
		computer.setIntroduced(ConvertDate.convert(scan.nextLine()));

		System.out.println("Date d'arret : (yyyy-MM-dd)");
		computer.setDiscontinued(ConvertDate.convert(scan.nextLine()));

		System.out.println("ID de l'entreprise");
		Optional<Company> optionalCompany = DAOcompany.getInstance().getCompanyById(scan.nextInt());
		if(optionalCompany.isPresent()){	
			Company company = optionalCompany.get();
			computer.setCompany(company);
		}else 
			System.out.println("Company ID not found!");
		DAOcomputer.getInstance().createComputer(computer);
	}

	public void updateComputer() throws SQLException {
		System.out.println("Entrer un ID a modifier");
		long majId = scan.nextLong();
		scan.nextLine();
		Optional<Computer> optionnalComputer = DAOcomputer.getInstance().getComputerById(majId);
		System.out.println(optionnalComputer.toString());
		if(optionnalComputer.isPresent()){
			Computer oldComputer = optionnalComputer.get();
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
			Optional<Company> optionalCompany = DAOcompany.getInstance().getCompanyById(company_id);
			if(optionalCompany.isPresent()){	
				Company company = optionalCompany.get();
				if(EcompanyId.isEmpty()) {
					oldComputer.company = oldComputer.company;
					DAOcomputer.getInstance().updateComputer(newComputer);
				}
				else {
					newComputer.setCompany(company);
					DAOcomputer.getInstance().updateComputer(newComputer);
				}
			}else 
				System.out.println("Company not found!");
		}else
			System.out.println("Computer not found!");
	}
	
	public void deleteComputer() throws SQLException {
		System.out.println("Entrer un ID");
		int suppId = scan.nextInt();
		DAOcomputer.getInstance().deleteComputer(suppId);
		
	}
	
	public void pagination() {
		Pagination page = new Pagination(DAOcomputer.getInstance().countAllComputer());
		ArrayList<Computer> computerPage = new ArrayList<Computer>();
		computerPage = DAOcomputer.getInstance().getPageComputers(page);
		page.displayPageContent(computerPage);
		
		boolean quit = true;
		while(quit) {
			System.out.println("prev page : p | page " + page.getPageNum() + "/" + page.getPageMax() + " | next page : n | quit q");
			String input = scan.nextLine();
				switch(input) {
				case "p" :
					page.prevPage();
					computerPage = DAOcomputer.getInstance().getPageComputers(page);
					page.displayPageContent(computerPage);
					break;
				case "n" :
					page.nextPage();
					computerPage = DAOcomputer.getInstance().getPageComputers(page);
					page.displayPageContent(computerPage);
					break;
				case "q" :
					quit = false;
					break;
				default :
					System.out.println("Entrée incorecte");
				}
		}
	}

}
