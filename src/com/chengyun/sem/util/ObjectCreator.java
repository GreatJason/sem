package com.chengyun.sem.util;

import java.lang.reflect.InvocationTargetException;
import static com.chengyun.sem.util.Logger.*;

public class ObjectCreator<T> {
	T tObj = null;
	public ObjectCreator(Class<T> clazz){
		try {
			tObj = clazz.newInstance();
		} catch (InstantiationException e) {
			error(e);
		} catch (IllegalAccessException e) {
			error(e);
		}
	}
	
	public void put(String fieldName, Object value){
		String method = "set" + fieldName.replaceFirst(String.valueOf(fieldName.charAt(0)), 
															String.valueOf(fieldName.charAt(0)).toUpperCase());
		try {
			tObj.getClass().getMethod(method, value.getClass()).invoke(tObj, value);
		} catch (IllegalAccessException e) {
			Logger.error(e);
		} catch (IllegalArgumentException e) {
			Logger.error(e);
		} catch (InvocationTargetException e) {
			Logger.error(e);
		} catch (NoSuchMethodException e) {
			Logger.error(e);
		} catch (SecurityException e) {
			Logger.error(e);
		}
	}
	
	public T getInstance(){
		return tObj;
	}
}
