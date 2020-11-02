package com.robertompfm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.robertompfm.dao.ContactDaoImp;
import com.robertompfm.model.Contact;

/**
 * Servlet implementation class ContactControllerServlet
 */
@WebServlet("/contact-controller-servlet")
public class ContactControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Contact contact;
	private ContactDaoImp dao;
    
    public ContactControllerServlet() {
        this.contact = new Contact();
        this.dao = new ContactDaoImp();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Called by servlet").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String active = request.getParameter("active");
	}

}
