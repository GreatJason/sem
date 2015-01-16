package com.chengyun.sem.factory;

import com.chengyun.sem.dao.ErrorDao;
import com.chengyun.sem.dao.UserDao;
import com.chengyun.sem.mysqldao.ErrorDaoImpl;
import com.chengyun.sem.mysqldao.UserDaoImpl;

public class DaoFactory {
	public static UserDao getUserDao(){
		return new UserDaoImpl();
	}
	public static ErrorDao getErrorDao(){
		return new ErrorDaoImpl();
	}
}
