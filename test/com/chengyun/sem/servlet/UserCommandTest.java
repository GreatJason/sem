package com.chengyun.sem.servlet;

import org.junit.Test;

public class UserCommandTest {

	@Test
	public void test(){
		UserCommand command = UserCommand.newInstance(10);
		switch(command){
		case Login:
			System.out.println("login");
			break;
		default:
			System.out.println("default");
			break;
		}
	}
}
