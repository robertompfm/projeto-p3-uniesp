package com.robertompfm.service;

import java.util.List;

import com.robertompfm.dao.ContactDao;
import com.robertompfm.dao.ContactDaoImp;
import com.robertompfm.model.Contact;

public class ContactServiceImp implements ContactService {
	
	private ContactDao contactDao;
	
	public ContactServiceImp() {
	
		contactDao = new ContactDaoImp();
	}

	@Override
	public boolean saveContact(Contact contact) {
		return contactDao.saveContact(contact);
	}

	@Override
	public List<Contact> contactsList() {
		return contactDao.contactsList();
	}

	@Override
	public boolean removeContact(long contactId) {
		return contactDao.removeContact(contactId);
	}

	@Override
	public Contact findContactById(long contactId) {
		return contactDao.findContactById(contactId);
	}
	
	@Override
	public boolean toggleContactStatus(long contactId) {
		Contact contact = findContactById(contactId);
		if (contact == null) return false;
		contact.setActive(!contact.isActive());
		return contactDao.updateContact(contact);
	}

	@Override
	public boolean isContactEmailValid(String email) {
		Contact contact = contactDao.findContactByEmail(email);
		return contact == null;
	}

	@Override
	public boolean updateContact(Contact contact) {
		return contactDao.updateContact(contact);
	}

}
