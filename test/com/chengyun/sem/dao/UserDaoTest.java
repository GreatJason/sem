package com.chengyun.sem.dao;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.chengyun.sem.factory.DaoFactory;
import com.chengyun.sem.model.User;
import com.chengyun.sem.mysqldao.UserDaoImpl;

public class UserDaoTest {
	
private UserDao userDao = DaoFactory.getUserDao();
	
	@Test
	public void getUser(){
		String userName = "jasonfan";
		userDao = new UserDaoImpl();
		User user = userDao.getUser(userName);
		Assert.assertEquals(userName, user.getUserName());
	}
	
	@Test
	public void updateUser(){
		User user = userDao.getUser("jasonfan");
		user.setVerifyCode("123456");
		user.setVerifyTime(new Date());
		Assert.assertTrue(userDao.updateUser(user));
	}
	
	@Test
	public void addUser(){
		User user = new User();
		user.setUserName("test01");
		user.setPassword("abcdefg");
		Assert.assertTrue(userDao.addUser(user));
	}
	
	@Test
	public void deleteUser(){
		Assert.assertTrue(userDao.deleteUser("fanliwei3"));
	}
}
