package com.excilys.ui;

public enum CliMenu {
	LISTCOMPANIES, LISTCOMPUTERS, SHOWDETAILS, CREATECOMPUTER, UPDATECOMPUTER, DELETECOMPUTER, PAGINATION, QUIT;

	public static CliMenu menuChoice(int choice) {
		switch (choice) {
		case 1:
			return LISTCOMPANIES;
		case 2:
			return LISTCOMPUTERS;
		case 3:
			return SHOWDETAILS;
		case 4:
			return CREATECOMPUTER;
		case 5:
			return UPDATECOMPUTER;
		case 6:
			return DELETECOMPUTER;
		case 7:
			return PAGINATION;
		case 8:
			return QUIT;
		default:
			return QUIT;
		}
	}
}
