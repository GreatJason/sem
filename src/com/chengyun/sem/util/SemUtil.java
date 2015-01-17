package com.chengyun.sem.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;

public class SemUtil {
	/**
	 * words in columnName must be separated by "_"
	 * @param columnName
	 * @return
	 */
	public static String columnName2FieldName(String columnName){
		String[] words = columnName.split("_");
		StringBuilder fieldName = new StringBuilder();
		for(int i = 0; i < words.length; ++i){
			String word = words[i].toLowerCase();
			if(i > 0){
				word = word.replaceFirst(String.valueOf(word.charAt(0)), 
									String.valueOf(word.charAt(0)).toUpperCase());
			}
			fieldName.append(word);
		}
		return fieldName.toString();
	}
	
	public static Date string2Date(String dateStr){
		try{
			if(dateStr == null)
				return null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
			return format.parse(dateStr);	
		} catch(ParseException e){
			return null;
		}
	}
	
	public static String date2String(Date date){
		if(date == null)
			return null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
		return format.format(date);
	}
	
	public static String toJson(Object src){
		Gson gson = new Gson();
		return gson.toJson(src);
	}
	public static <T> T fromJson(String json, Class<T> classOfT){
		Gson gson = new Gson();
		return gson.fromJson(json, classOfT);
	}
}
