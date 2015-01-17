package com.chengyun.sem.bll;

import com.chengyun.sem.dao.ErrorDao;
import com.chengyun.sem.factory.DaoFactory;
import com.chengyun.sem.util.SemUtil;

public class ErrorManage {
	private static ErrorDao errorDao = DaoFactory.getErrorDao();
	
	public static String getAllErrorInfos(){
		return SemUtil.toJson(errorDao.getErrors());
	}
}
