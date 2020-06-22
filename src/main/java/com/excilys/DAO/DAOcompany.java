package com.excilys.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.excilys.mapper.Mapper;
import com.excilys.model.Company;
import com.excilys.persistence.Connexion;

@Repository
public class DAOcompany {

	private Connexion connexion;

	public DAOcompany(Connexion connexion) {
		this.connexion = connexion;
	}

	public List<Company> getCompanies(){
		ResultSet allCompaniesRes;
		Optional<Company> company;
		List<Company> listCompanies = new ArrayList<Company>();

		try (PreparedStatement getCompaniesStatement = connexion.getConn().prepareStatement(SQLRequest.GETCOMPANIES.getQuery())) {
			allCompaniesRes = getCompaniesStatement.executeQuery();
			while (allCompaniesRes.next()) {
				company = Mapper.companyMapper(allCompaniesRes);
				listCompanies.add(company.get());
			}
		} catch (SQLException SQLexception) {
			SQLexception.printStackTrace();
		}
		return listCompanies;
	}

	public Optional<Company> getCompanyById(long id){
		ResultSet companyRes;
		Optional<Company> company;
		try (PreparedStatement getCompanyByIdStatement = connexion.getConn().prepareStatement(SQLRequest.GETCOMPANYBYID.getQuery())) {
			getCompanyByIdStatement.setLong(1, id);
			companyRes = getCompanyByIdStatement.executeQuery();
			if (companyRes.next()) {
				company = Mapper.companyMapper(companyRes);
				return company;
			}
		} catch (SQLException SQLexception) {
			SQLexception.printStackTrace();
		}
		return Optional.empty();
	}
	
	public void deleteCompany(long id){
		try (PreparedStatement deleteCompanyStatement = connexion.getConn().prepareStatement(SQLRequest.DELETECOMPANY.getQuery())) {
			deleteCompanyStatement.setLong(1, id);
			deleteCompanyStatement.executeUpdate();
		} catch (SQLException SQLexception) {
			SQLexception.printStackTrace();
		}
	}
}
