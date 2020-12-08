package com.robertompfm.service;

import java.util.List;

import com.robertompfm.model.Contact;

public interface ContactService {

	public boolean saveContact(Contact contact);
	
	public List<Contact> contactsList();
	
	public boolean removeContact(long contactId);
	
	public Contact findContactById(long contactId);
	
	public boolean isContactEmailValid(String email);

	public boolean toggleContactStatus(long contactId);
	
	public boolean updateContact(Contact contact);
}
