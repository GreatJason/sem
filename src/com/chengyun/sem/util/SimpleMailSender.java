package com.chengyun.sem.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * SimpleMailSender with on attachment
 */
public class SimpleMailSender {
	public boolean sendTextMailFrom163(String toAddress, String subject, String content) {
		Session session = Session.getInstance(getPerperties163());
		try {
			Message msg = new MimeMessage(session);
			msg.setSubject(subject);
			msg.setText(content);
			msg.setFrom(new InternetAddress("fanliwei96@163.com"));
			
			Transport transport = session.getTransport();
			transport.connect("fanliwei96@163.com", "flw_621234987@");
			transport.sendMessage(msg, new Address[]{new InternetAddress(toAddress)});
			
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
			return false;
		}
	}
	public boolean sendTextMailFromQQ(String toAddress, String subject, String content) {
		Session session = Session.getInstance(getPerpertiesQQ());
		try {
			Message msg = new MimeMessage(session);
			msg.setSubject(subject);
			msg.setText(content);
			msg.setFrom(new InternetAddress("administrator@chengyun.cc"));
			
			Transport transport = session.getTransport();
			transport.connect("administrator@chengyun.cc", "flw621234987");
			transport.sendMessage(msg, new Address[]{new InternetAddress(toAddress)});
			
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public Properties getPerperties163(){
		Properties p = new Properties();  
//    	p.put("mail.debug", "true");
    	p.put("mail.smtp.auth", "true");
    	p.put("mail.smtp.host", "smtp.163.com");   
    	p.put("mail.transport.protocol", "smtp");   
    	return p; 
	}
	
	public Properties getPerpertiesQQ(){
		Properties p = new Properties();  
//		p.put("mail.debug", "true");
    	p.put("mail.smtp.auth", "true");
    	p.put("mail.smtp.host", "smtp.qq.com");   
    	p.put("mail.transport.protocol", "smtp");      
    	return p; 
	}
}