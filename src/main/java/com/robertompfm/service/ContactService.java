package com.robertompfm.service;

import java.util.List;

import com.robertompfm.model.Contact;

public interface ContactService {

	
	public void saveContact(Contact contact);
	
	public List<Contact> contactsList();
	
	public void removeContact(long contactId);
	
	public Contact findContactById(long contactId);
}
