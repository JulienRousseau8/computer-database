package com.excilys.ui;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.Connexion;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

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

	public static void afficherChoix() {
		System.out.println("");
		System.out.println("Effectuez une nouvelle requete (9 pour afficher le menu ou 8 pour quitter)");
	}
	
	public static void actionsMenu() throws SQLException {
		ComputerService computerService = new ComputerService();
		CompanyService companyService = new CompanyService();

		boolean quit = true;
		Scanner sc = new Scanner(System.in);
		while (quit) {
			int choice = sc.nextInt();
			switch (CliMenu.menuChoice(choice)) {
				case LISTCOMPANIES:
					List<Company> companies = companyService.getAllCompanies();
//					for (Company companies : companyService.getAllCompanies()) {
//						System.out.println(companies.toString());
//					}
					companies.stream().forEach(System.out::println);
					afficherChoix();
					break;
				case LISTCOMPUTERS:
					List<Computer> computers = computerService.getAllComputers();
					computers.stream().forEach(System.out::println);
					afficherChoix();
					break;
				case SHOWDETAILS:
					ActionsMenu.getInstance().showDetails();
					afficherChoix();
					break;
				case CREATECOMPUTER:
					ActionsMenu.getInstance().createComputer();
					afficherChoix();
					break;
				case UPDATECOMPUTER:
					ActionsMenu.getInstance().updateComputer();
					afficherChoix();
					break;
				case DELETECOMPUTER:
					ActionsMenu.getInstance().deleteComputer();
					afficherChoix();
					break;
				case PAGINATION:
					ActionsMenu.getInstance().pagination();
					afficherChoix();
					break;
				case QUIT:
					quit = false;
					break;
				case AFFICHERMENU:
					afficherMenu();
					break;
				default:
					break;
			}
		}
		sc.close();
	}

	public static void main(String[] args) throws SQLException {
		
		Connexion.getDbCon();
		afficherMenu();
		actionsMenu();
	}
}
