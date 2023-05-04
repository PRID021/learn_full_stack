package com.example.demo.data.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserAccount> {

	public UserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		String id = rs.getString("ID");
		String userName = rs.getNString("USER_NAME");
		Date date = rs.getDate("CREATE_TIME");
		Boolean activeStatus = rs.getBoolean("ACTIVE_STATUS");
		UserAccount user = new UserAccount(id, userName, date, activeStatus);
		return user;
	}

}