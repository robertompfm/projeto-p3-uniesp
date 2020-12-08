package com.robertompfm.dao;

import java.util.List;

import com.robertompfm.model.Contact;

public interface ContactDao {
	
	public boolean saveContact(Contact contact);
	
	public List<Contact> contactsList();
	
	public boolean removeContact(long contactId);
	
	public Contact findContactById(long contactId);
	
	public Contact findContactByEmail(String email);
	
	public boolean updateContact(Contact contact);
	
	public boolean updateContactStatus(long contactId, int active);

}
