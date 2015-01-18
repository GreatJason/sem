package com.chengyun.sem.model;

public enum ErrorCode {
	SUCCEED(0),
	GENERAL_ERROR(1),
	NULL_OBJECT(2);
	
	private final int value;
	
	ErrorCode(int value){
		this.value = value;
	}
	
	@Override
	public String toString(){
		return String.valueOf(value);
	}
}
