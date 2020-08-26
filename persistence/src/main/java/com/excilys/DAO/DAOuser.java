package com.excilys.DAO;

import java.util.List;
import java.util.Optional;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.model.MyUser;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class DAOuser {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Optional<MyUser> getUserById(long id){
		Session session = this.sessionFactory.getCurrentSession();
		String queryString = HQLRequest.GETUSERBYID.getQuery();
		TypedQuery<MyUser> query = session.createQuery(queryString).setParameter("id",id);
		return Optional.of(query.getSingleResult());
	}

	public List<MyUser> getUsers(){
		Session session = this.sessionFactory.getCurrentSession();
		String queryString = HQLRequest.GETUSERS.getQuery();
		TypedQuery<MyUser> query = session.createQuery(queryString);
		return query.getResultList();
	}

	public Optional<MyUser> getUserByName(String name){
		Session session = this.sessionFactory.getCurrentSession();
		String queryString = HQLRequest.GETUSERBYNAME.getQuery();
		TypedQuery<MyUser> query = session.createQuery(queryString).setParameter("name",name);
		return Optional.of(query.getSingleResult());
	} 
	
	public void createUser(MyUser user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(user);
	}
	
	public void deleteUser(long id) {
		Session session = this.sessionFactory.getCurrentSession();
		String queryString = HQLRequest.DELETEUSER.getQuery();
		TypedQuery<MyUser> query = session.createQuery(queryString).setParameter("id", id);
		query.executeUpdate();
	}
}
