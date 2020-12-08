package com.robertompfm.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;

import com.robertompfm.model.Contact;
import com.robertompfm.utils.JPAUtil;

public class ContactDaoImp implements ContactDao {

	EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public boolean saveContact(Contact contact) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			em.merge(contact);
			et.commit();
			em.close();
			return true;
		} catch (Exception e) {
			em.close();
			return false;
		}
	
		
	}

	@Override
	public List<Contact> contactsList() {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			Query query = em.createQuery("SELECT c FROM Contact c");
			List<Contact> list = query.getResultList();
			return list;
		} catch (Exception e) {
			return new ArrayList<Contact>();
		}
		
	}

	@Override
	public boolean removeContact(long contactId) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		try {
			Contact contact = em.find(Contact.class, contactId);
			em.remove(contact);
			et.commit();
			em.close();
			return true;
		} catch (Exception e) {
			em.close();
			return false;
		}
	}

	@Override
	public Contact findContactById(long contactId) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			Contact contact = em.find(Contact.class, contactId);
			em.close();
			return contact;	
		} catch (Exception e) {
			em.close();
			return null;
		}
	}
	
	@Override
	public boolean updateContactStatus(long contactId, int active) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			String updateString = "UPDATE Contact c SET c.active = " +
				active + "WHERE c.id = " + contactId;
			em.createQuery(updateString).executeUpdate();
			et.commit();
			em.close();
			return true;
		} catch (Exception e) {
			em.close();
			return false;
		}
	}
	
	@Override
	public Contact findContactByEmail(String email) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			Contact contact = em.createNamedQuery("Contact.searchByEmail", Contact.class)
					.setParameter("email", email)
					.getSingleResult();
			em.close();
			return contact;
		} catch (Exception e) {
			em.close();
			return null;
		}
	}

	@Override
	public boolean updateContact(Contact contact) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			Contact contactDB = em.find(Contact.class, contact.getId());
			if (contactDB == null) {
				em.close();
				return false;
			}
			em.merge(contact);
			et.commit();
			em.close();
			return true;
		} catch (Exception e) {
			em.close();
			return false;
		}
	}
	

}
