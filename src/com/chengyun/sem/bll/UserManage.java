package com.chengyun.sem.bll;

import java.util.Date;
import java.util.Random;

import com.chengyun.sem.dao.UserDao;
import com.chengyun.sem.factory.DaoFactory;
import com.chengyun.sem.model.User;
import com.chengyun.sem.util.Logger;
import com.chengyun.sem.util.MailSenderInfo;
import com.chengyun.sem.util.SimpleMailSender;

public class UserManage {
	private UserDao userDao = DaoFactory.getUserDao();
	private static UserManage userManage = new UserManage();

	private UserManage(){}
	
	public static UserManage getInstance(){
		return userManage;
	}
	
	public User getUser(String username){
		return userDao.getUser(username);
	}
	
	public boolean login(String userName, String password){
		User user = userDao.getUser(userName);
		if(user!= null){
			return password.equals(user.getPassword());
		}
		return false;
	}
	
	public boolean addUser(String userName, String password){
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setEmail(userName);
		return userDao.addUser(user);
	}
	
	public boolean updatePassword(String username, String password, String verifyCode){
		User user = userDao.getUser(username);
		if(user != null){
			if(verifyCode.equals(user.getVerifyCode())){
				//check timeout
				Date now = new Date();
				long interval = now.getTime() - user.getVerifyTime().getTime();
				if(interval > (2 * 60 * 60 * 1000)){ //2 hours millseconds
					return false;
				}
				//update
				user.setPassword(password);
				user.setVerifyCode(null);
				user.setVerifyTime(null);
				return userDao.updateUser(user);
			}
		}
		return false;
	}
	
	public String generateVerifyCode(String username){
		User user = userDao.getUser(username);
		if(user != null){
			Random rand = new Random(); 
			String verifyCode = String.valueOf(rand.nextInt(900000) + 100000);
			//send email
			MailSenderInfo mailInfo = new MailSenderInfo();   
			mailInfo.setMailServerHost("smtp.qq.com");   
			mailInfo.setMailServerPort("25");		
			mailInfo.setValidate(true);
			mailInfo.setUserName("test@chengyun.cc");
			mailInfo.setPassword("flw621234987");      
			mailInfo.setFromAddress("test@chengyun.cc");
			mailInfo.setToAddress(user.getEmail());
			mailInfo.setSubject("验证码");
			mailInfo.setContent("验证码:" + verifyCode);
			SimpleMailSender sms = new SimpleMailSender(); 
			if(sms.sendTextMail(mailInfo)){
				//update user in db
				user.setVerifyCode(verifyCode);
				user.setVerifyTime(new Date());
				if(userDao.updateUser(user)){
					return verifyCode;
				} else{
					Logger.error("fail to update verify code and verify date for " + user.getUserName());
				}
			} else{
				Logger.error("fail to send email to " + user.getUserName());
			}
		} else{
			Logger.error("user: " + username + " does not exist!");
		}
		return null;
	}
	
	public boolean deleteUser(String username){
		return userDao.deleteUser(username);
	}
}
