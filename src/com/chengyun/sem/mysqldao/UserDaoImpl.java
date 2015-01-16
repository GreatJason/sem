package com.chengyun.sem.mysqldao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import com.chengyun.sem.dao.UserDao;
import com.chengyun.sem.model.User;
import com.chengyun.sem.util.Logger;
import com.chengyun.sem.util.SemUtil;
import com.chengyun.sem.util.dbutil.DbSupport;

public class UserDaoImpl implements UserDao{
	private DbSupport db = DbSupport.getInstance();
	
	@Override
	public User getUser(String userName) {
		return null;
	}

	@Override
	public boolean addUser(User user) {
		try {
			String sql = "insert into user(user_id, user_name, password, realname, mobile, email, create_time) values(?, ?, ?, ?, ?, ?, current_time)";
			PreparedStatement pstmt = db.connect().prepareStatement(sql);
			int i = 0;
			pstmt.setString(++i, UUID.randomUUID().toString());
			pstmt.setString(++i, user.getUserName());
			pstmt.setString(++i, user.getPassword());
			pstmt.setString(++i, user.getRealname());
			pstmt.setString(++i, user.getMobile());
			pstmt.setString(++i, user.getEmail());
			pstmt.execute();
			return true;
		} catch (SQLException e) {
			Logger.error(e.getMessage());
		} finally{
			db.disconnect();
		}
		return false;
	}

	@Override
	public boolean updateUser(User user) {
		try{
			String sql = "update user set user_name = ?, password=?, realname=?, mobile=?, email=?, verify_code=?, verify_time=? where user_id=?";
			PreparedStatement pstmt = db.connect().prepareStatement(sql);
			int i = 0;
			pstmt.setString(++i, user.getUserName());
			pstmt.setString(++i, user.getPassword());
			pstmt.setString(++i, user.getRealname());
			pstmt.setString(++i, user.getMobile());
			pstmt.setString(++i, user.getEmail());
			pstmt.setString(++i, user.getVerifyCode());
			pstmt.setString(++i, SemUtil.date2String(user.getVerifyTime()));
			pstmt.setString(++i, user.getUserId());
			pstmt.executeUpdate();
			return true;
		} catch(SQLException e){
			Logger.error(e);
		} finally{
			db.disconnect();
		}
		return false;
	}

	@Override
	public boolean deleteUser(String userId) {
		try {
			String sql = "delete from user where user_id = ?";
			PreparedStatement pstmt = db.connect().prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.disconnect();
		}
		return false;
	}
	
	
}

