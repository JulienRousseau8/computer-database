package com.excilys.ui;

import java.sql.SQLException;
import java.util.Scanner;

import com.excilys.DAO.*;
import com.excilys.model.*;
import com.excilys.persistence.MySQLConnect;

public class UI {

	public static void afficherMenu() {
		System.out.println("*************************************************");
		System.out.println("	1 - Afficher la liste des entreprises");
		System.out.println("	2 - Afficher la liste des ordinateurs");
		System.out.println("	3 - Afficher les détails d'un ordinateur");
		System.out.println("	4 - Ajouter un ordinateur");
		System.out.println("	5 - Mettre à jour un ordinateur");
		System.out.println("	6 - Supprimer un ordinateur");
		System.out.println("	7 - Pagination ordinateur");
		System.out.println("	8 - Quitter");
		System.out.println("*************************************************");
	}
	
	public static void actionsMenu() throws SQLException {
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		
		switch(CliMenu.menuChoice(choice)) {
			case LISTCOMPANIES :
				//System.out.println(DAOcompany.getInstance().getCompanies());
				for(Company companies : DAOcompany.getInstance().getCompanies()) {
					System.out.println(companies.toString());
				}
				break;
			case LISTCOMPUTERS : 
				//System.out.println(computer.getComputers());
				for(Computer computer : DAOcomputer.getInstance().getComputers()) {
					System.out.println(computer.toString());
				}
				break;
			case SHOWDETAILS :
				ActionsMenu.getInstance().showDetails();
				break;	
			case CREATECOMPUTER :
				ActionsMenu.getInstance().createComputer();
				break;
			case UPDATECOMPUTER :
				ActionsMenu.getInstance().updateComputer();
				break;		
			case DELETECOMPUTER :
				ActionsMenu.getInstance().deleteComputer();
				break;	
			case PAGINATION : 
				ActionsMenu.getInstance().pagination();
			case QUIT :
				break;	
			default :
				break;
		}
		sc.close();
	}
	
	public static void main(String[] args) throws SQLException {
		MySQLConnect.getDbCon();
		afficherMenu();
		actionsMenu();
		
	}
}

