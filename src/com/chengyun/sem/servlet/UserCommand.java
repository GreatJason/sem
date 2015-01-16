package com.chengyun.sem.servlet;

public enum UserCommand {
	Login(1),
	Register(2),
	GetVerifyCode(3),
	UpdatePassword(4),
	NULL(-1);
	
	public final int value;
	
	UserCommand(int value){
		this.value = value;
	}
	
	public static UserCommand newInstance(int value){
		UserCommand[] commands = UserCommand.values();
		for(UserCommand command:commands){
			if(value == command.value){
				return command;
			}
		}
		return NULL;
	}
	
	@Override
	public String toString(){
		return String.valueOf(value);
	}
}
