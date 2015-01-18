package com.chengyun.sem.util;

import org.junit.Assert;
import org.junit.Test;

public class SimpleMailSenderTest {

	private String toAddress = "fanliwei96@163.com";
	private String subject = "修改用户密码：验证码";
	private String content = "验证码:" + 10000;
	
	@Test
	public void sendMailFromQQTest(){
		Assert.assertTrue(new SimpleMailSender().sendTextMailFromQQ(toAddress, subject, content)); 
	}
	
	@Test
	public void sendMailFrom163Test(){
		Assert.assertTrue(new SimpleMailSender().sendTextMailFrom163(toAddress, subject, content));
	}
}
