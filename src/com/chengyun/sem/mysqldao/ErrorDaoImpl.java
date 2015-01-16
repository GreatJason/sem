package com.chengyun.sem.mysqldao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.chengyun.sem.dao.ErrorDao;
import com.chengyun.sem.model.ErrorInfo;
import com.chengyun.sem.util.Logger;
import com.chengyun.sem.util.dbutil.DbSupport;
import com.google.common.collect.Lists;

public class ErrorDaoImpl implements ErrorDao{

	@Override
	public List<ErrorInfo> getErrors() {
		String sql = "SELECT ERROR_NO, ERROR_CODE, ERROR_DESC FROM ERROR_INFO";
		List<ErrorInfo> errors = Lists.newArrayList();
		try {
			PreparedStatement pstmt = DbSupport.getInstance().connect().prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				ErrorInfo err = new ErrorInfo();
				err.setErrorNo(rs.getInt("ERROR_NO"));
				err.setErrorCode(rs.getString("ERROR_CODE"));
				err.setErrorDesc(rs.getString("ERROR_DESC"));
				errors.add(err);
			}
		} catch (SQLException e) {
			Logger.error(e);
			throw new RuntimeException(e);
		}
		return errors;
	}

}
