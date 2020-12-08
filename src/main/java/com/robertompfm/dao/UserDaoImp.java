package com.robertompfm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.robertompfm.model.User;
import com.robertompfm.utils.JPAUtil;

public class UserDaoImp implements UserDao {

	EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.merge(user);
		et.commit();
		em.close();
		
	}

	@Override
	public List<User> usersList() {
		// TODO Auto-generated method stub
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		Query query = em.createQuery("SELECT c FROM User c");
		List<User> list = query.getResultList();
		return list;
		
	}

	@Override
	public void removeUser(long userId) {
		// TODO Auto-generated method stub
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		User user = em.find(User.class, userId);
		em.remove(user);
		et.commit();
		em.close();
		
		
	}

	@Override
	public User findUserById(long userId) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		User user = em.find(User.class, userId);
		em.close();
		return user;
	}

	@Override
	public User findUserBylogin(String login) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		Query query = em.createQuery("SELECT u FROM User u WHERE user_login = :login").setParameter("login", login);
		List<User> users = query.getResultList();
		User user;
		if (users.size() != 0) {
			user = users.get(0);
		} else {
			user = null;
		}
		em.close();
		return user;
	}
	

}
