package com.excilys.DAO;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.excilys.mapper.ComputerRSMapper;
import com.excilys.model.Computer;
import com.excilys.model.Pagination;

@Repository
public class DAOcomputer {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	private ComputerRSMapper computerMapper = new ComputerRSMapper();
	
	public DAOcomputer(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Computer> getComputers() {
		return namedJdbcTemplate.query(SQLRequest.GETCOMPUTERS.getQuery(),this.computerMapper);
	}

	public Optional<Computer> getComputerById(long id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("id", id);
		Computer computer = namedJdbcTemplate.queryForObject(SQLRequest.GETCOMPUTERBYID.getQuery(),namedParameters, this.computerMapper);
		return Optional.of(computer);
	}

	public List<Computer> getComputersByCompanyId(long id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("id", id);
		return namedJdbcTemplate.query(SQLRequest.GETCOMPUTERSBYCOMPANYID.getQuery(),namedParameters, this.computerMapper);
	}

	public void createComputer(Computer computer) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("name", computer.getName())
				.addValue("introduced", computer.getIntroduced())
				.addValue("discontinued", computer.getDiscontinued())
				.addValue("company.id", computer.getCompany().getId());
		namedJdbcTemplate.update(SQLRequest.CREATECOMPUTER.getQuery(), namedParameters);
		
	}

	public void updateComputer(Computer computer) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("name", computer.getName())
				.addValue("introduced", computer.getIntroduced())
				.addValue("discontinued", computer.getDiscontinued())
				.addValue("company.id", computer.getCompany().getId())
				.addValue("computer.id", computer.getId());
		namedJdbcTemplate.update(SQLRequest.UPDATECOMPUTER.getQuery(), namedParameters);
	}

	public void deleteComputer(long id) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("id", id);
		namedJdbcTemplate.update(SQLRequest.DELETECOMPUTER.getQuery(), namedParameters);
	}

	public int countAllComputer() {
		return jdbcTemplate.queryForObject(SQLRequest.COUNTCOMPUTERS.getQuery(), Integer.class);
	}

	public List<Computer> getPageComputers(Pagination page) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("1", page.getPageNum() * page.getPageTaille())
				.addValue("2", page.getPageTaille());
		return namedJdbcTemplate.query(SQLRequest.GETPAGECOMPUTERS.getQuery(), namedParameters, this.computerMapper);
	}

	public List<Computer> getSearchComputersPage(String recherche, Pagination page) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("search", "%" + recherche + "%")
				.addValue("1", page.getPageNum() * page.getPageTaille())
				.addValue("2", page.getPageTaille());
		return namedJdbcTemplate.query(SQLRequest.SEARCHCOMPUTERPAGE.getQuery(), namedParameters, this.computerMapper);

	}

	public List<Computer> getSearchComputers(String recherche) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("search", "%" + recherche + "%");
		return namedJdbcTemplate.query(SQLRequest.SEARCHCOMPUTER.getQuery(), namedParameters, this.computerMapper);
	}

	public List<Computer> getPageComputersOrdered(Pagination page, String orderBy, int direction) {
		final int DIR = 1;
		final String GETPAGECOMPUTERORDERBYNAMEASC = "SELECT computer.id, computer.name, computer.introduced , computer.discontinued , company_id, company.name "
				+ "FROM computer LEFT JOIN company ON company_id = company.id ORDER BY " + orderBy + " ASC LIMIT :1, :2;";
		final String GETPAGECOMPUTERORDERBYNAMEDESC = "SELECT computer.id, computer.name, computer.introduced , computer.discontinued , company_id, company.name "
				+ "FROM computer LEFT JOIN company ON company_id = company.id ORDER BY " + orderBy + " DESC LIMIT :1, :2;";
		
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("1", page.getPageNum() * page.getPageTaille())
				.addValue("2", page.getPageTaille());
		
		if(direction == DIR) {
			return namedJdbcTemplate.query(GETPAGECOMPUTERORDERBYNAMEASC, namedParameters, this.computerMapper);
			}
		else {
			return namedJdbcTemplate.query(GETPAGECOMPUTERORDERBYNAMEDESC, namedParameters, this.computerMapper);
		}	
	}
}






