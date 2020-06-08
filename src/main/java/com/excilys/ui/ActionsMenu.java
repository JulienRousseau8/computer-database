package com.excilys.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.dto.ComputerDTO;
import com.excilys.model.Computer;
import com.excilys.model.Pagination;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

public class ActionsMenu {

	private static Logger logger = LoggerFactory.getLogger(ActionsMenu.class);

	private Scanner scan = new Scanner(System.in);
	public static ActionsMenu actionMenu;
	ComputerService computerService = new ComputerService();
	CompanyService companyService = new CompanyService();

	private ActionsMenu() {
	}

	public static ActionsMenu getInstance() {
		if (actionMenu == null) {
			actionMenu = new ActionsMenu();
		}
		return actionMenu;
	}

	public void showDetails(){
		System.out.println("Entrer un ID");
		String detailId = scan.nextLine();
		System.out.println(computerService.getComputerById(detailId).get());
	}

	public void createComputer(){
		System.out.println("Entrer un nom");
		String computerName = scan.nextLine();

		System.out.println("Date d'introduction (yyyy-MM-dd):");
		String dateIntro = scan.nextLine();

		System.out.println("Date d'arret : (yyyy-MM-dd)");
		String dateArret = scan.nextLine();

		System.out.println("ID de l'entreprise");
		String companyId = scan.nextLine();

		ComputerDTO computerDTO = new ComputerDTO.ComputerDTOBuilder().setName(computerName).setIntroduced(dateIntro)
				.setDiscontinued(dateArret).setCompanyId(companyId).build();
		computerService.createComputer(computerDTO);
	}

	public void updateComputer(){
		System.out.println("Entrer un ID a modifier");
		String stringId = scan.nextLine();
		Optional<Computer> optComputer = computerService.getComputerById(stringId);
		if (optComputer.isPresent()) {
			System.out.println(optComputer.get());
		}

		System.out.println();
		System.out.println("Un champ vide gardera l'ancienne valeur");
		System.out.println("Nouveau nom :");
		String computerName = scan.nextLine();

		System.out.println("Nouvelle date d'introduction : (yyyy-MM-dd)");
		String dateIntro = scan.nextLine();

		System.out.println("Nouvelle date d'arret : (yyyy-MM-dd)");
		String dateArret = scan.nextLine();

		System.out.println("Modifier ID de l'entreprise : ");
		String companyId = scan.nextLine();

		ComputerDTO computerDTO = new ComputerDTO.ComputerDTOBuilder().setId(stringId).setName(computerName)
				.setIntroduced(dateIntro).setDiscontinued(dateArret).setCompanyId(companyId).build();

		computerService.updateComputer(computerDTO);
	}

	public void deleteComputer(){
		System.out.println("Entrer un ID");
		int suppId = scan.nextInt();
		computerService.deleteComputer(suppId);
	}

	public void pagination() {
		Pagination page = new Pagination(computerService.countAllComputer(), 20);
		List<Computer> computerPage = new ArrayList<Computer>();
		computerPage = computerService.getPageComputer(page);
		page.displayPageContent(computerPage);

		boolean quit = true;
		while (quit) {
			System.out.println("prev page : p | page " + page.getPageNum() + "/" + page.getPageMax()
					+ " | next page : n | quit q");
			String input = scan.nextLine();
			switch (input) {
			case "p":
				page.prevPage();
				computerPage = computerService.getPageComputer(page);
				page.displayPageContent(computerPage);
				break;
			case "n":
				page.nextPage();
				computerPage = computerService.getPageComputer(page);
				page.displayPageContent(computerPage);
				break;
			case "q":
				quit = false;
				break;
			default:
				logger.info("Entrée incorecte");
			}
		}
	}

}
