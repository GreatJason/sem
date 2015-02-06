package com.chengyun.sem.bll;

import java.util.Date;
import java.util.Random;

import com.chengyun.sem.dao.UserDao;
import com.chengyun.sem.factory.DaoFactory;
import com.chengyun.sem.model.OnlineStatus;
import com.chengyun.sem.model.User;
import com.chengyun.sem.util.Logger;
import com.chengyun.sem.util.SimpleMailSender;

public class UserManage {
	private static UserDao userDao = DaoFactory.getUserDao();

	private UserManage(){}
	
	public static User getUser(String username){
		return userDao.getUser(username);
	}
	
	public static boolean login(String userName, String password){
		User user = userDao.getUser(userName);
		if(user!= null){
			if(userDao.updateOnlineStatus(user.getUserId(), OnlineStatus.ONLINE.toInt())){
				return password.equals(user.getPassword());	
			}
		} 
		return false;
	}
	
	public static boolean logout(String userName){
		User user = userDao.getUser(userName);
		if(user != null){
			return userDao.updateOnlineStatus(user.getUserId(), OnlineStatus.OFFLINE.toInt());
		} 
		return false;
	}
	
	public static boolean addUser(String userName, String password){
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setEmail(userName);
		return userDao.addUser(user);
	}
	
	public static boolean updatePassword(String username, String password, String verifyCode){
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
				return userDao.updateUser(user);
			}
		}
		return false;
	}
	
	public static String generateVerifyCode(String username){
		User user = userDao.getUser(username);
		if(user != null){
			Random rand = new Random(); 
			String verifyCode = String.valueOf(rand.nextInt(900000) + 100000);
			//send email
			String subject = "���ƿƼ�������֤";
			String content = username + "\n"
							+ "����!"
							+ "������֤��Ϊ:" + verifyCode + "\n" 
							+ "ע�⣺�˲������ܻ��޸��������롣��Ǳ��˲������뼰ʱ��¼���޸������Ա�֤�˻���ȫ��" + "\n"
							+ "���ʼ�Ϊϵͳ�Զ����ͣ�����ظ���";
			SimpleMailSender sms = new SimpleMailSender(); 
			if(sms.sendTextMailFromQQ(user.getEmail(), subject, content)){
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
	
	public static boolean deleteUser(String username){
		return userDao.deleteUser(username);
	}
}
