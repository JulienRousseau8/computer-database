package com.excilys.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

import com.excilys.DAO.*;
import com.excilys.persistence.*;
import com.excilys.model.*;
public class Test {	

	public static void main(String[] args) throws SQLException {
		
		MySQLConnect mysql = MySQLConnect.getDbCon();
		DAOcompany company = new DAOcompany();
		DAOcomputer computer = new DAOcomputer();
		ArrayList<Company> listCompanies = new ArrayList<Company>();
		ArrayList<Computer> listComputers = new ArrayList<Computer>();
		
		System.out.println("1 - Afficher la liste des entreprises");
		System.out.println("2 - Afficher la liste des ordinateurs");
		System.out.println("3 - Afficher les détails d'un ordinateur");
		System.out.println("4 - Ajouter un ordinateur");
		System.out.println("5 - Mettre à jour un ordinateur");
		System.out.println("6 - Supprimer un ordinateur");
		System.out.println("7 - Quitter");
		
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		
		switch(CliMenu.menuChoice(choice)) {
		
		case LISTCOMPANIES :
			listCompanies = company.getCompanies();
			for(Company _companies : listCompanies) {
				System.out.println(_companies.toString());
			}
			break;
				
		case LISTCOMPUTERS : 
			listComputers = computer.getComputers();
			for(Computer _computer : listComputers) {
				System.out.println(_computer.toString());
			}
			break;
			
		case SHOWDETAILS :
			System.out.println("Entrer un ID");
			int detailId = sc.nextInt();
			Optional<Computer> _computer = computer.getComputerById(detailId);
			System.out.println(_computer.toString());;
			break;
			
		case CREATECOMPUTER :
			System.out.println("Entrer un nom");
			String name = sc.nextLine();
			Date introduced = null;
			Date discontinued = null;
			long company_id = 3;
			computer.createComputer(name, introduced, discontinued, company_id);
		case UPDATECOMPUTER :
			
			
		case DELETECOMPUTER :
			
			
		case QUIT :
			
		default :
			System.out.println("Invalid choice");
		}
	}	
		
}
