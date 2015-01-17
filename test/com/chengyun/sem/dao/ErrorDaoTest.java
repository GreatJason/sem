package com.chengyun.sem.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.chengyun.sem.factory.DaoFactory;
import com.chengyun.sem.model.ErrorInfo;
import com.chengyun.sem.util.SemUtil;
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
	
	@SuppressWarnings("unchecked")
	@Test
	public void errorToJsonTest(){
		List<ErrorInfo> errors = errorDao.getErrors();
		Assert.assertNotNull(errors);
		for(ErrorInfo error:errors){
			Assert.assertTrue(error.getErrorNo() > 0);
			Assert.assertTrue(!Strings.isNullOrEmpty(error.getErrorCode()));
			Assert.assertTrue(!Strings.isNullOrEmpty(error.getErrorDesc()));
		}
		String json = SemUtil.toJson(errors);
		System.out.println(json);
		List<ErrorInfo> errorsFromJson = (List<ErrorInfo>)SemUtil.fromJson(json, new ArrayList<ErrorInfo>().getClass());
		System.out.println(errorsFromJson);
	}
}
