package com.robertompfm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.robertompfm.dao.ContactDaoImp;
import com.robertompfm.model.Contact;
import com.robertompfm.service.ContactService;
import com.robertompfm.service.ContactServiceImp;
import com.robertompfm.utils.Constants;

/**
 * Servlet implementation class ContactControllerServlet
 */
@WebServlet("/contact-controller-servlet")
public class ContactControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Contact contact;
	private ContactService service;
	private List<Contact> contacts;
    
    public ContactControllerServlet() {
    	this.contact = new Contact();
        this.service = new ContactServiceImp();
        this.contacts = new ArrayList<Contact>();
    }
    
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		contacts = service.contactsList();
		RequestDispatcher rd = request.getRequestDispatcher(Constants.CONTACT_LIST);
		request.setAttribute("contacts", this.contacts);
		rd.forward(request, response);
	}

    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		switch (action) {
			case "createNewContact":
				createNewContact(request, response);
				break;
			case "toggleContactStatus":
				toggleContactStatus(request, response);
				break;
			case "redirectToUpdateContact":
				redirectToUpdateContact(request, response);
				break;
			case "updateContact":
				updateContact(request, response);
				break;
			case "seeContactInfo":
				seeContactInfo(request, response);
				break;
			case "removeContact":
				removeContact(request, response);
				break;
		}

	}
	
	
	private void createNewContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.contact = new Contact();
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		boolean active = true;
		
		this.contact.setName(name);
		this.contact.setEmail(email);
		this.contact.setPhone(phone);
		this.contact.setActive(active);
		
		boolean validEmail = this.service.isContactEmailValid(email);
		
		boolean success = validEmail && this.service.saveContact(contact);
		
		if (success) {
			request.setAttribute("success", "Contato " + name + " cadastrado com sucesso");
		} else if (!validEmail) {
			request.setAttribute("fail", "Já existe um contato com o email " + email);
		} else {
			request.setAttribute("fail", "Não foi possível salvar o contato " + name);
		}
		
		doGet(request, response);
	}

	
	private void toggleContactStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long contactId = Long.parseLong(request.getParameter("id"));
		String name = request.getParameter("name");
		boolean success = service.toggleContactStatus(contactId);
//		boolean success = false;
		RequestDispatcher rd = request.getRequestDispatcher(Constants.CONTACT_LIST);
		
		if (success) {
			request.setAttribute("success", ("O status do contato " + name + " foi alterado com sucesso"));
		} else {
			request.setAttribute("fail", ("Não foi possível mudar o status do contato " + name));
		}
		List<Contact> contacts = service.contactsList();
		request.setAttribute("contacts", contacts);
		rd.forward(request, response);
	}
	
	
	private void removeContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long contactId = Long.parseLong(request.getParameter("id"));
		String name = request.getParameter("name");
		boolean success = service.removeContact(contactId);
		RequestDispatcher rd = request.getRequestDispatcher(Constants.CONTACT_LIST);
		
		if (success) {
			request.setAttribute("success", ("O contato " + name + " foi removido da lista"));
		} else {
			request.setAttribute("fail", ("Não foi possível remover o contato " + name));
		}
		List<Contact> contacts = service.contactsList();
		request.setAttribute("contacts", contacts);
		rd.forward(request, response);
	}
	
	
	private void redirectToUpdateContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long contactId = Long.parseLong(request.getParameter("id"));
		String name = request.getParameter("name");
		this.contact = service.findContactById(contactId);
		RequestDispatcher rd;
		if (contact == null) {
			request.setAttribute("fail", ("Ocorreu um erro ao buscar as informações do contato " + name));
			rd = request.getRequestDispatcher(Constants.CONTACT_LIST);
		} else {
			rd = request.getRequestDispatcher(Constants.CONTACT_EDIT);
			request.setAttribute("id", contact.getId());
			request.setAttribute("name", contact.getName());
			request.setAttribute("phone", contact.getPhone());
			request.setAttribute("email", contact.getEmail());
		}
		rd.forward(request, response);
	}
	
	
	private void updateContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.contact = new Contact();

		long contactId = Long.parseLong(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		boolean active = true;
		
		this.contact.setId(contactId);
		this.contact.setName(name);
		this.contact.setEmail(email);
		this.contact.setPhone(phone);
		this.contact.setActive(active);
		
		Contact contactDB = service.findContactById(contactId);		
		if (contactDB == null) {
			request.setAttribute("fail", ("Ocorreu um erro ao buscar as informações do contato " + name));
		
		} else {
			boolean validEmail =  contactDB.getEmail().equals(email) || this.service.isContactEmailValid(email);
			boolean success = validEmail && this.service.updateContact(contact);
			
			if (success) {
				request.setAttribute("success", "Contato " + name + " atualizado com sucesso");
			
			} else if (!validEmail) {
				request.setAttribute("fail", "Já existe um contato com o email " + email);
			
			} else {
				request.setAttribute("fail", "Não foi possível atualizar o contato " + name);
			
			}
		}
		
		doGet(request, response);
	}
	
	
	private void seeContactInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long contactId = Long.parseLong(request.getParameter("id"));
		String name = request.getParameter("name");
		this.contact = service.findContactById(contactId);
		RequestDispatcher rd;
		if (contact == null) {
			request.setAttribute("fail", ("Ocorreu um erro ao buscar as informações do contato " + name));
			rd = request.getRequestDispatcher(Constants.CONTACT_LIST);
		} else {
			rd = request.getRequestDispatcher(Constants.CONTACT_INFO);
			request.setAttribute("id", contact.getId());
			request.setAttribute("name", contact.getName());
			request.setAttribute("phone", contact.getPhone());
			request.setAttribute("email", contact.getEmail());
		}
		rd.forward(request, response);
	}
	
	
}








