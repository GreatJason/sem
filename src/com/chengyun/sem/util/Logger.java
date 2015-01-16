package com.chengyun.sem.util;

import org.slf4j.LoggerFactory;

public class Logger {
	private static org.slf4j.Logger LOGGER;
	
	public static void trace(Object obj){
		if(obj instanceof Exception){
			trace(((Exception)obj).getMessage());
			((Exception)obj).printStackTrace();
		} else{
			trace(obj.toString());
		}
	}
	public static void trace(String msg){
		LOGGER.trace(msg);
	}
	public static void trace(String format, Object... args){
		LOGGER.trace(format, args);
	}
	public static void debug(Object obj){
		if(obj instanceof Exception){
			debug(((Exception)obj).getMessage());
			((Exception)obj).printStackTrace();
		} else{
			debug(obj.toString());
		}
	}
	public static void debug(String msg){
		LOGGER.debug(msg);
	}
	public static void debug(String format, Object... args){
		LOGGER.debug(format, args);
	}
	public static void info(Object obj){
		if(obj instanceof Exception){
			info(((Exception)obj).getMessage());
			((Exception)obj).printStackTrace();
		} else{
			info(obj.toString());
		}
	}
	public static void info(String msg){
		LOGGER.info(msg);
	}
	public static void info(String format, Object... args){
		LOGGER.info(format, args);
		
	}
	public static void error(String msg){
		
	}
	public static void error(Object obj){
		if(obj instanceof Exception){
			error(((Exception)obj).getMessage());
			((Exception)obj).printStackTrace();
		} else{
			error(obj.toString());
		}
	}
	public static void error(String format, Object... args){
		
	}
	static{
		LOGGER = LoggerFactory.getLogger("sem");
	}
}
