package com.chengyun.sem.bll;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.chengyun.sem.model.User;

public class UserManageTest {
	private String username = "fanliwei96@163.com";
	private String password = encryptToMD5("testpassword");
	private String newPwd = encryptToMD5("newpassword");

	@Test
	public void mainTest(){
		addUserTest();
		loginTest();
		updatePasswordTest();
		deleteUserTest();
	}
	
	public void addUserTest() {
		Assert.assertTrue(UserManage.getInstance().addUser(username, password));
	}

	public void loginTest() {
		UserManage.getInstance().login(username, password);
		System.out.println(encryptToMD5(password));
	}

	public void updatePasswordTest() {
		String verifyCode = UserManage.getInstance().generateVerifyCode(username);
		Assert.assertTrue(UserManage.getInstance().updatePassword(username, newPwd, verifyCode));
		User user = UserManage.getInstance().getUser(username);
		Assert.assertEquals(newPwd, user.getPassword());
	}
	
	public void deleteUserTest(){
		Assert.assertTrue(UserManage.getInstance().deleteUser(username));
	}

	private String encryptToMD5(String info) {
		byte[] digesta = null;
		try {
			// 得到一个md5的消息摘要
			MessageDigest alga = MessageDigest.getInstance("MD5");
			// 添加要进行计算摘要的信息
			alga.update(info.getBytes());
			// 得到该摘要
			digesta = alga.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// 将摘要转为字符串
		String rs = byte2hex(digesta);
		return rs;
	}

	/**
	 * 将二进制转化为16进制字符串
	 * 
	 * @param b
	 *            二进制字节数组
	 * @return String
	 */
	private String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}
}
