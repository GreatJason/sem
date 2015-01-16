package com.chengyun.sem.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

public class SemUtilTest {
//	@Test
	public void columnName2FieldNameTest(){
		String columnName = "test_column_name";
		String actual = SemUtil.columnName2FieldName(columnName);
		Assert.assertEquals("testColumnName", actual);
	}
	
	@Test
	public void test(){
		String regex ="\\w+(?=(?:\\sNOT\\s))(?=(\\sIN\\s))";
		String sql = "ACCOUNT_CLASS NOT IN ('100','200')";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(sql);
		while(m.find()){
			System.out.println(m.group());
		}
	}
}
