package com.chengyun.sem.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.chengyun.sem.factory.DaoFactory;
import com.chengyun.sem.model.ErrorInfo;
import com.google.common.base.Strings;

public class ErrorDaoTest {
	
	private ErrorDao errorDao = DaoFactory.getErrorDao();
	
	@Test
	public void getErrorsTest(){
		List<ErrorInfo> errors = errorDao.getErrors();
		Assert.assertNotNull(errors);
		for(ErrorInfo error:errors){
			Assert.assertTrue(error.getErrorNo() > 0);
			Assert.assertTrue(!Strings.isNullOrEmpty(error.getErrorCode()));
			Assert.assertTrue(!Strings.isNullOrEmpty(error.getErrorDesc()));
		}
	}
}
