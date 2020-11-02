package com.robertompfm.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import com.robertompfm.model.Contact;
import com.robertompfm.utils.JPAUtil;

public class ContactDaoImp implements ContactDao {

	EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
	
	@Override
	public void saveContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Contact> contactsList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeContact(long contactId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Contact findContactById(long contactId) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
