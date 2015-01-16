package com.chengyun.sem.other;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import org.junit.Test;

public class TestClass {

	@Test
	public void dateTest(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
		try {
			if(null == format.parse(null)){
				System.out.println("ok");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void uuidTest(){
		System.out.println(UUID.randomUUID().toString().length());
	}
}
