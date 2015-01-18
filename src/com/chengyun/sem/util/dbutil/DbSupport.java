package com.chengyun.sem.util.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.chengyun.sem.util.Logger.*;

public class DbSupport {
	private Connection conn;
	private final String driver = "com.mysql.jdbc.Driver";
	private final String url = "jdbc:mysql://127.0.0.1:3306/sem";
	private final String user = "root";
	private final String password = "root";
	private static DbSupport instance = new DbSupport();
	
	private DbSupport(){
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			error(e);
		}
	}

	public static DbSupport getInstance(){
		return instance;
	}
	public Connection connect() throws SQLException{
		conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
	
	public void disconnect(){
		try{
			conn.close();
		} catch(SQLException e){
			error(e);
		}
	}
}
