package com.chengyun.sem.util.dbutil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chengyun.sem.util.Logger;
import com.chengyun.sem.util.ObjectCreator;

public class TableClassMapper{
	DbSupport db = DbSupport.getInstance();
	
	public <T> List<T> sql2Class(String sql, Class<T> clazz){
		List<T> results = new ArrayList<>();
		try {
			PreparedStatement pstmt = db.connect().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			int colSize = rs.getMetaData().getColumnCount();
			while(rs.next()){
				ObjectCreator<T> creator = new ObjectCreator<T>(clazz);
				for(int i = 0; i < colSize; ++i){
					String colName = rs.getMetaData().getColumnName(i);
					Object value = rs.getObject(i);
					creator.put(colName, value);
				}
				results.add(creator.getInstance());
			}
		} catch (SQLException e) {
			Logger.error(e);
		} finally{
			db.disconnect();
		}
		return results.size() == 0 ? null : results;
	}

}
