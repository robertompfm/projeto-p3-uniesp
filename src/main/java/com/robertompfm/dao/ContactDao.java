package com.robertompfm.dao;

import java.util.List;

import com.robertompfm.model.Contact;

public interface ContactDao {
	
	public void saveContact(Contact contact);
	
	public List<Contact> contactsList();
	
	public void removeContact(long contactId);
	
	public Contact findContactById(long contactId);

}
