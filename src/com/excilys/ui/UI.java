package com.excilys.ui;

import java.sql.SQLException;
import java.util.Scanner;

import com.excilys.DAO.DAOcompany;
import com.excilys.DAO.DAOcomputer;
import com.excilys.model.Company;
import com.excilys.model.Computer;

public class UI {

	public static void main(String[] args) throws SQLException {
		DAOcompany company = new DAOcompany();
		DAOcomputer computer = new DAOcomputer();
		ActionsMenu actionsmenu = new ActionsMenu();
		
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
			//System.out.println(company.getCompanies());
			for(Company _companies : company.getCompanies()) {
				System.out.println(_companies.toString());
			}
			break;
				
		case LISTCOMPUTERS : 
			//System.out.println(computer.getComputers());
			for(Computer _computer : computer.getComputers()) {
				System.out.println(_computer.toString());
			}
			break;
			
		case SHOWDETAILS :
			actionsmenu.showDetails();
			break;
			
		case CREATECOMPUTER :
			actionsmenu.createComputer();
			break;
			
		case UPDATECOMPUTER :
			
			
		case DELETECOMPUTER :
			actionsmenu.deleteComputer();
			break;
			
		case QUIT :
			break;
			
		default :
			break;
		}

	}	
}

