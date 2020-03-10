package com.excilys.ui;

import java.sql.SQLException;
import java.util.Scanner;

import com.excilys.DAO.*;
import com.excilys.mapper.ConvertDate;
import com.excilys.model.*;

public class ActionsMenu {

	public Scanner scan = new Scanner(System.in);
	DAOcomputer daocomputer = new DAOcomputer();
	DAOcompany daocompany = new DAOcompany();


	public void showDetails() throws SQLException {
		System.out.println("Entrer un ID");
		int detailId = scan.nextInt();
		Computer computer = daocomputer.getComputerById(detailId);
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
		Company company = daocompany.getCompanyById(scan.nextInt());

		computer.setCompany(company);
		daocomputer.createComputer(computer);
	}

	public void updateComputer() throws SQLException {

		System.out.println("Entrer un ID a modifier");
		long majId = scan.nextLong();
		Computer computer = daocomputer.getComputerById(majId);
		System.out.println(computer.toString());

		System.out.println("nouveau nom :");
		computer.setName(scan.nextLine());
		
		System.out.println("nouvelle date d'introduction : (yyyy-MM-dd)");
		computer.setIntroduced(ConvertDate.convert(scan.nextLine()));
		
		System.out.println("Date d'arret : (yyyy-MM-dd)");
		computer.setDiscontinued(ConvertDate.convert(scan.nextLine()));

		System.out.println("modifier ID de l'entreprise : ");
		Company company = daocompany.getCompanyById(scan.nextLong());

		computer.setCompany(company);
		daocomputer.updateComputer(computer);
	}

	public void deleteComputer() throws SQLException {
		System.out.println("Entrer un ID");
		int suppId = scan.nextInt();
		daocomputer.deleteComputer(suppId);
	}

}
