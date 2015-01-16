package com.chengyun.sem.util;

import org.junit.Assert;
import org.junit.Test;

import com.chengyun.sem.model.User;

public class ObjectCreatorTest {
	@Test
	public void createClassInstanceTest(){
		ObjectCreator<User> oc = new ObjectCreator<User>(User.class);
		oc.put("username", "zhangsan");
		User user = oc.getInstance();
		Assert.assertNotNull(user);
		Assert.assertEquals("zhangsan", user.getUserName());
	}
}
