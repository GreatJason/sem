package com.chengyun.sem.dao;

import com.chengyun.sem.model.User;

public interface UserDao{
	User getUser(String username);
	boolean addUser(User user);
	boolean updateUser(User user);
	boolean deleteUser(String username);
	public boolean updateOnlineStatus(String userId, int onlineStatus);
}
