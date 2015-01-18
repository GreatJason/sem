package com.chengyun.sem.model;

import java.util.List;

import com.google.common.collect.Lists;


public enum OnlineStatus {
	OFFLINE(0),
	ONLINE(1);
	
	private final int value;
	
	private OnlineStatus(int value){
		this.value = value;
		OnlineStatusHelper.allStatuses.add(this);
	}
	
	@Override
	public String toString(){
		return String.valueOf(value);
	}
	
	public static OnlineStatus valueOf(int status){
		for(OnlineStatus statusEnum:OnlineStatusHelper.allStatuses){
			if(statusEnum.value == status){
				return statusEnum;
			}
		}
		return null;
	}
	
	static class OnlineStatusHelper{
		public static List<OnlineStatus> allStatuses = Lists.newArrayList();
	}
}

