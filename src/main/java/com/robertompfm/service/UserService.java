package com.robertompfm.service;

import java.util.List;

import com.robertompfm.model.User;

public interface UserService {
	
	public void saveUser(User user);
	
	public List<User> usersList();
	
	public void removeUser(long userId);
	
	public User findUserById(long userId);
	
	public boolean hasUser(String login);
	
	public boolean validLogin(String login, String password);
}
