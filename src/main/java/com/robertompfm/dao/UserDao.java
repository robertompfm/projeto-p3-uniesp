package com.robertompfm.dao;

import java.util.List;


import com.robertompfm.model.User;

public interface UserDao {
	
	public void saveUser(User user);
	
	public List<User> usersList();
	
	public void removeUser(long userId);
	
	public User findUserById(long userId);
	
	public User findUserBylogin(String login);

}
