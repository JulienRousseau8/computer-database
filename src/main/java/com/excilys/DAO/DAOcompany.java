package com.excilys.DAO;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.excilys.mapper.CompanyRSMapper;
import com.excilys.model.Company;

@Repository
public class DAOcompany {

	private NamedParameterJdbcTemplate namedJdbcTemplate;
	private CompanyRSMapper companyMapper = new CompanyRSMapper();

	public DAOcompany(DataSource dataSource) {
		this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Company> getCompanies(){
		return namedJdbcTemplate.query(SQLRequest.GETCOMPANIES.getQuery(),this.companyMapper);
	}

	public Optional<Company> getCompanyById(long id){
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("id", id);
		Company company = namedJdbcTemplate.queryForObject(SQLRequest.GETCOMPANYBYID.getQuery(),namedParameters, this.companyMapper);
		return Optional.of(company);
	}
	
	public void deleteCompany(long id){
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("id", id);
		namedJdbcTemplate.update(SQLRequest.DELETECOMPANY.getQuery(), namedParameters);
	}
}
