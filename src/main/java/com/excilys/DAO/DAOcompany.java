package com.excilys.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import com.excilys.mapper.Mapper;
import com.excilys.model.Company;
import com.excilys.persistence.MySQLConnect;

public class DAOcompany {

	public static DAOcompany daoCompany;

	private static final String GETCOMPANIES = "SELECT company.id, company.name FROM company";
	private static final String GETCOMPANYBYID = "SELECT id, name FROM company WHERE id=?";

	private DAOcompany() {
	}

	public static DAOcompany getInstance() {
		if (daoCompany == null) {
			daoCompany = new DAOcompany();
		}
		return daoCompany;
	}

	public ArrayList<Company> getCompanies() throws SQLException {
		ResultSet allCompaniesRes;
		Optional<Company> company;
		ArrayList<Company> listCompanies = new ArrayList<Company>();

		try (PreparedStatement st = MySQLConnect.conn.prepareStatement(GETCOMPANIES)) {
			allCompaniesRes = st.executeQuery();
			while (allCompaniesRes.next()) {
				company = Mapper.companyMapper(allCompaniesRes);
				listCompanies.add(company.get());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCompanies;
	}

	public Optional<Company> getCompanyById(long id) throws SQLException {
		ResultSet companyRes;
		Optional<Company> company;
		try (PreparedStatement st = MySQLConnect.conn.prepareStatement(GETCOMPANYBYID)) {
			st.setLong(1, id);
			companyRes = st.executeQuery();
			if (companyRes.first()) {
				company = Mapper.companyMapper(companyRes);
				return company;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
}
