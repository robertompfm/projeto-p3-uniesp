package com.robertompfm.service;

import java.util.List;

import com.robertompfm.dao.UserDao;
import com.robertompfm.dao.UserDaoImp;
import com.robertompfm.model.User;

public class UserServiceImp implements UserService {
	
	private UserDao userDao;
	
	public UserServiceImp() {
		userDao = new UserDaoImp();
	}

	@Override
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

	@Override
	public List<User> usersList() {
		return userDao.usersList();
	}

	@Override
	public void removeUser(long userId) {
		userDao.removeUser(userId);
	}

	@Override
	public User findUserById(long userId) {
		return userDao.findUserById(userId);
	}

	@Override
	public boolean hasUser(String login) {
		User user = userDao.findUserBylogin(login);
		return user != null;
	}

	@Override
	public boolean validLogin(String login, String password) {
		User user = userDao.findUserBylogin(login);
		return (user != null && user.getPassword().equals(password));
	}


}
