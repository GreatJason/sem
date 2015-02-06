package com.chengyun.sem.model;

public enum Command {
	Usage("Usage"),
	Login("Login"),
	Register("Register"),
	GetVerifyCode("GetVerifyCode"),
	Logout("Logout"),
	UpdatePassword("UpdatePassword");
	
	public final String value;
	
	Command(String value){
		this.value = value;
	}
	
	@Override
	public String toString(){
		return value;
	}
}
