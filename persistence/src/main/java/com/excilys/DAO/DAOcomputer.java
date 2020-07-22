package com.excilys.DAO;

import java.util.List;
import java.util.Optional;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.model.Computer;
import com.excilys.model.Pagination;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class DAOcomputer {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Computer> getComputers(){
		Session session = this.sessionFactory.getCurrentSession();
		String queryString = HQLRequest.GETCOMPUTERS.getQuery();
		TypedQuery<Computer> query = session.createQuery(queryString);
		return query.getResultList();

	}

	public Optional<Computer> getComputerById(long id){
		Session session = this.sessionFactory.getCurrentSession();
		String queryString = HQLRequest.GETCOMPUTERBYID.getQuery();
		TypedQuery<Computer> query = session.createQuery(queryString).setParameter("id",id);
		return Optional.of(query.getSingleResult());

	}

	public List<Computer> getComputersByCompanyId(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		String queryString = HQLRequest.GETCOMPUTERSBYCOMPANYID.getQuery();
		TypedQuery<Computer> query = session.createQuery(queryString).setParameter("id",id);
		return query.getResultList();

	}

	public void createComputer(Computer computer) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(computer);
	}

	public void updateComputer(Computer computer) {
		Session session = this.sessionFactory.getCurrentSession();
		String queryString = HQLRequest.UPDATECOMPUTER.getQuery();
		TypedQuery<Computer> query = session.createQuery(queryString)
				.setParameter("name", computer.getName())
				.setParameter("introduced", computer.getIntroduced())
				.setParameter("discontinued", computer.getDiscontinued())
				.setParameter("companyId", computer.getCompany().getId())
				.setParameter("id",computer.getId());
		query.executeUpdate();
	}

	public void deleteComputer(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		String queryString = HQLRequest.DELETECOMPUTER.getQuery();
		TypedQuery<Computer> query = session.createQuery(queryString).setParameter("id", id);
		query.executeUpdate();
	}

	public long countAllComputer() {
		Session session = this.sessionFactory.getCurrentSession();
		String queryString = HQLRequest.COUNTCOMPUTERS.getQuery();
		Query<Long> query = session.createQuery(queryString);
		return (long) query.uniqueResult();
	}

	public List<Computer> getPageComputers(Pagination page) {
		Session session = this.sessionFactory.getCurrentSession();
		String queryString = HQLRequest.GETCOMPUTERS.getQuery();
		TypedQuery<Computer> query = session.createQuery(queryString);
		query.setFirstResult( page.getPageNum() * page.getPageTaille());
		query.setMaxResults(page.getPageTaille());
		return query.getResultList();
	}

	public List<Computer> getSearchComputersPage(String recherche, Pagination page) {	
		Session session = this.sessionFactory.getCurrentSession();
		String queryString = HQLRequest.SEARCHCOMPUTER.getQuery();
		TypedQuery<Computer> query = session.createQuery(queryString)
				.setParameter("search", "%" + recherche + "%");

		query.setFirstResult( page.getPageNum() * page.getPageTaille());
		query.setMaxResults(page.getPageTaille());
		return query.getResultList();
	}

	public List<Computer> getSearchComputers(String recherche) {
		Session session = this.sessionFactory.getCurrentSession();
		String queryString = HQLRequest.SEARCHCOMPUTER.getQuery();
		TypedQuery<Computer> query = session.createQuery(queryString)
				.setParameter("search", "%" + recherche + "%");
		return query.getResultList();
	}

	public List<Computer> getPageComputersOrdered(Pagination page, String orderBy, int direction) {
		final int DIR = 1;
		Session session = this.sessionFactory.getCurrentSession();

		if(direction == DIR) {
			String queryString = HQLRequest.GETPAGECOMPUTERORDERBYNAME.getQuery() + orderBy  + " ASC";
			TypedQuery<Computer> query = setParameterOrderBy(page, session, queryString);
			return query.getResultList();
		}
		else {
			String queryString = HQLRequest.GETPAGECOMPUTERORDERBYNAME.getQuery() + orderBy + " DESC";
			TypedQuery<Computer> query = setParameterOrderBy(page, session, queryString);
			return query.getResultList();
		}	
	}

	private TypedQuery<Computer> setParameterOrderBy(Pagination page, Session session, String queryString) {
		TypedQuery<Computer> query = session.createQuery(queryString);
		query.setFirstResult( page.getPageNum() * page.getPageTaille());
		query.setMaxResults(page.getPageTaille());
		return query;
	}
}






