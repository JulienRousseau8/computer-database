package com.excilys.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.configuration.HibernateConfig;
import com.excilys.configuration.SpringConfig;
import com.excilys.model.Company;
import com.excilys.model.Computer;

public class UI {

	private final ActionsMenu actionsMenu;

	private UI(ActionsMenu actionsMenu) {
		this.actionsMenu = actionsMenu;
	}
	
	private void afficherMenu() throws SQLException {
		System.out.println("*************************************************");
		System.out.println("	1 - Afficher la liste des entreprises");
		System.out.println("	2 - Afficher la liste des ordinateurs");
		System.out.println("	3 - Afficher les détails d'un ordinateur");
		System.out.println("	4 - Ajouter un ordinateur");
		System.out.println("	5 - Mettre à jour un ordinateur");
		System.out.println("	6 - Supprimer un ordinateur");
		System.out.println("	7 - Pagination ordinateur");
		System.out.println("	8 - Quitter");
		System.out.println("   	9 - Afficher le menu");
		System.out.println("    10- Supprimer une entreprise");
		System.out.println("*************************************************");
		actionsMenu();
	}

	private void afficherChoix() {
		System.out.println("");
		System.out.println("Effectuez une nouvelle requete (9 pour afficher le menu ou 8 pour quitter)");
	}

	private void actionsMenu() throws SQLException {
		boolean quit = true;
		Scanner sc = new Scanner(System.in);
		while (quit) {
			int choice = sc.nextInt();
			switch (CliMenu.menuChoice(choice)) {
				case LISTCOMPANIES:
					List<Company> companies = actionsMenu.getAllCompanies();
					companies.forEach(System.out::println);
					afficherChoix();
					break;
				case LISTCOMPUTERS:
					List<Computer> computers = actionsMenu.getAllComputers();
					computers.forEach(System.out::println);
					afficherChoix();
					break;
				case SHOWDETAILS:
					actionsMenu.showDetails();
					afficherChoix();
					break;
				case CREATECOMPUTER:
					actionsMenu.createComputer();
					afficherChoix();
					break;
				case UPDATECOMPUTER:
					actionsMenu.updateComputer();
					afficherChoix();
					break;
				case DELETECOMPUTER:
					actionsMenu.deleteComputer();
					afficherChoix();
					break;
				case PAGINATION:
					actionsMenu.pagination();
					afficherChoix();
					break;
				case QUIT:
					quit = false;
					break;
				case AFFICHERMENU:
					afficherMenu();
					break;
				case DELETECOMPANY:
					actionsMenu.deleteCompany();
					break;
				default:
					break;
			}
		}
		sc.close();
	}

	private static void startApp() throws SQLException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(SpringConfig.class, HibernateConfig.class);
		context.refresh();
		ActionsMenu actionsMenu = context.getBean(ActionsMenu.class);
		UI ui = new UI(actionsMenu);
		ui.afficherMenu();
		context.close();
	}

	public static void main(String[] args) throws SQLException {
		startApp();
	}
}
