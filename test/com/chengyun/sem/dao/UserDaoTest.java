package com.chengyun.sem.dao;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.chengyun.sem.factory.DaoFactory;
import com.chengyun.sem.model.OnlineStatus;
import com.chengyun.sem.model.User;

public class UserDaoTest {
	
private UserDao userDao = DaoFactory.getUserDao();
	
	private String userName = "user01";
	private String password = "password";

	@Test
	public void succeedTest(){
		addUser();
		getUser();
		updateUser();
		deleteUser();
	}

	@Test
	public void addDuplicatedUserTest(){
		addUser();
		addUser();
		deleteUser();
	}
	
	public void getUser(){
		User user = userDao.getUser(userName);
		Assert.assertEquals(userName, user.getUserName());
	}
	
	public void getNotExistsUser(){
		String username = "notexistsusername";
		User user = userDao.getUser(username);
		Assert.assertNull(user);
	}
	
	public void updateUser(){
		User user = userDao.getUser("jasonfan");
		user.setVerifyCode("123456");
		user.setVerifyTime(new Date());
		Assert.assertTrue(userDao.updateUser(user));
	}
	
	public void addUser(){
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		Assert.assertTrue(userDao.addUser(user));
	}
	
	public void deleteUser(){
		Assert.assertTrue(userDao.deleteUser("fanliwei3"));
	}
}
