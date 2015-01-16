package com.chengyun.sem.util.dbutil;

import java.util.List;

import org.junit.Test;

import com.chengyun.sem.model.ErrorInfo;

public class TableClassMapperTest {

	@Test
	public void sql2ClassTest(){
		TableClassMapper mapper = new TableClassMapper();
		String sql = "select * from error_info";
		List<ErrorInfo> errs = mapper.sql2Class(sql, ErrorInfo.class);
		
	}
}
