package com.chengyun.sem.util.dbutil;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.chengyun.sem.model.ErrorInfo;

public class TableClassMapperTest {

	@Test
	public void sql2ClassTest(){
		TableClassMapper mapper = new TableClassMapper();
		String sql = "select * from error_info";
		List<ErrorInfo> errs = mapper.sql2Class(sql, ErrorInfo.class);
		Assert.assertNotNull(errs);
		for(ErrorInfo err:errs){
			Assert.assertNotNull(err.getErrorCode());
			Assert.assertNotNull(err.getErrorDesc());
			Assert.assertNotNull(err.getErrorNo());
		}
	}
}
