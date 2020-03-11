package com.excilys.ui;

import java.sql.SQLException;
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
		System.out.println("nouveau nom :");
		if(optionnalComputer.isPresent()){
			Computer computer = optionnalComputer.get();

			computer.setName(scan.nextLine());

			System.out.println("nouvelle date d'introduction : (yyyy-MM-dd)");
			computer.setIntroduced(ConvertDate.convert(scan.nextLine()));

			System.out.println("Date d'arret : (yyyy-MM-dd)");
			computer.setDiscontinued(ConvertDate.convert(scan.nextLine()));

			System.out.println("modifier ID de l'entreprise : ");
			Optional<Company> optionalCompany = DAOcompany.getInstance().getCompanyById(scan.nextLong());
			if(optionalCompany.isPresent()){	
				Company company = optionalCompany.get();
				computer.setCompany(company);
				DAOcomputer.getInstance().updateComputer(computer);
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

}
