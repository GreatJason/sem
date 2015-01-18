package com.chengyun.sem.util;

import org.junit.Assert;
import org.junit.Test;

public class SimpleMailSenderTest {

	private String toAddress = "fanliwei96@163.com";
	private String subject = "�޸��û����룺��֤��";
	private String content = "��֤��:" + 10000;
	
	@Test
	public void sendMailFromQQTest(){
		Assert.assertTrue(new SimpleMailSender().sendTextMailFromQQ(toAddress, subject, content)); 
	}
	
	@Test
	public void sendMailFrom163Test(){
		Assert.assertTrue(new SimpleMailSender().sendTextMailFrom163(toAddress, subject, content));
	}
}
