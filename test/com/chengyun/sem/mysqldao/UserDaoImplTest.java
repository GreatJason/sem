package com.chengyun.sem.mysqldao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import com.chengyun.sem.model.User;

public class UserDaoImplTest {

	private UserDaoImpl userDao = new UserDaoImpl();
	
	@Test
	public void getUser(){
		String userName = "jasonfan";
		userDao = new UserDaoImpl();
		User user = userDao.getUser(userName);
		Assert.assertEquals(userName, user.getUserName());
	}
	

	
	
}
