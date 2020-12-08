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
import javax.servlet.http.HttpSession;

import com.robertompfm.dao.UserDaoImp;
import com.robertompfm.model.User;
import com.robertompfm.service.UserService;
import com.robertompfm.service.UserServiceImp;
import com.robertompfm.utils.Constants;

/**
 * Servlet implementation class UserControllerServlet
 */
@WebServlet("/login-controller-servlet")
public class LoginControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService service;
	private List<User> users;
    
    public LoginControllerServlet() {
        this.service = new UserServiceImp();
    }
    


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");

		RequestDispatcher rd;
		
		if (service.hasUser(login)) {
			if (service.validLogin(login, password)) {
				HttpSession session = request.getSession();
				session.setAttribute("login", login);
				rd = request.getRequestDispatcher("index.jsp");
			} else {
				rd = request.getRequestDispatcher("login.jsp");	
				request.setAttribute("error", "Senha incorreta");
			}	
		} else {
			rd = request.getRequestDispatcher("login.jsp");	
			request.setAttribute("error", "O login inserido não existe");
		}
		
		rd.forward(request, response);
	}

}
