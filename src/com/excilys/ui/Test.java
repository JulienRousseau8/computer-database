package com.excilys.ui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.excilys.persistence.MySQLConnect;

public class Test {	

	public static void main(String[] args) throws SQLException {
		
		MySQLConnect mysql = MySQLConnect.getDbCon();
		
		String AllComputerQuery = "SELECT id,name,introduced,discontinued,company_id FROM computer";
		String AllCompanyQuery = "SELECT id,name FROM company";
		
		ResultSet AllComputerRes = mysql.query(AllComputerQuery);
		ResultSet AllCompanyRes = mysql.query(AllCompanyQuery);
		
		Scanner sc = new Scanner(System.in);
		String scan = sc.nextLine();
		
		switch(scan) {
			case "computer" :
				while(AllComputerRes.next()) {
					System.out.println(AllComputerRes.getString("id") + " | " + AllComputerRes.getString("name") + " | " + AllComputerRes.getString("introduced") + " | " + AllComputerRes.getString("discontinued")+ " | " + AllComputerRes.getString("company_id"));
				}
				break;
			case "company" : 
				while(AllCompanyRes.next()) {
					System.out.println(AllCompanyRes.getString("id") + " | " + AllCompanyRes.getString("name"));
				}
				break;
			default :
				System.out.println("Invalid choice");
		}	
	}

}
