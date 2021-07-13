package com.project.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.project.models.AdminData;
import com.project.models.Customer;

public class AdminDao {
	private JdbcTemplate jdbctemplate;
	public void setJdbctemplate(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
		}
	public int insertNewAdmin(String newAdminName,String newUsername,String newPassword) {
		String sql="insert into admindata(AdminName,Username,Password) values("+"'"+newAdminName+"'"+",'"+newUsername+"'"+",'"+newPassword+"')";   
	    return jdbctemplate.update(sql);
		
	}
	
	public List<AdminData> getData(String username, String password) {
		String query="select * from admindata where username='"+username+"' and password='"+password+"'";


		return jdbctemplate.query(query,new RowMapper<AdminData>(){
		public AdminData mapRow(ResultSet rs, int row) throws SQLException {
		AdminData adminData=new AdminData();

		adminData.setUsername(rs.getString("username"));
		adminData.setPassword(rs.getString("password"));
		
		return adminData;
		}
		});
	}
}
