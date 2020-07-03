package com.excilys.DAO;

import java.util.List;
import java.util.Optional;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.model.Company;

@Repository
@Transactional
public class DAOcompany {
	
	private SessionFactory sessionFactory;

	public DAOcompany(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Company> getCompanies(){
		Session session = this.sessionFactory.getCurrentSession();
		String queryString = HQLRequest.GETCOMPANIES.getQuery();
		TypedQuery<Company> query = session.createQuery(queryString);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public Optional<Company> getCompanyById(long id){
		Session session = this.sessionFactory.getCurrentSession();
		String queryString = HQLRequest.GETCOMPANYBYID.getQuery();
		TypedQuery<Company> query = session.createQuery(queryString).setParameter("id",id);
		return Optional.of(query.getSingleResult());
	}
	
	@SuppressWarnings("unchecked")
	public void deleteCompany(long id){
		Session session = this.sessionFactory.getCurrentSession();
		String queryString = HQLRequest.DELETECOMPANY.getQuery();
		TypedQuery<Company> query = session.createQuery(queryString).setParameter("id",id);
		query.executeUpdate();
	}
}
