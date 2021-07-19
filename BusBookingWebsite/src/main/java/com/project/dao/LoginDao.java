package com.project.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.project.models.AdminData;
import com.project.models.Customer;

public class LoginDao {
	private JdbcTemplate jdbctemplate;

	public void setJdbctemplate(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
	}

	public List<AdminData> getData(String username, String password) {
		String query = "select * from admindata where username='" + username + "' and password='" + password + "'";

		return jdbctemplate.query(query, new RowMapper<AdminData>() {
			public AdminData mapRow(ResultSet rs, int row) throws SQLException {
				AdminData adminData = new AdminData();

				adminData.setUsername(rs.getString("username"));
				adminData.setPassword(rs.getString("password"));

				return adminData;
			}
		});
	}

	public List<Customer> getUserData(String username, String password) {
		String query = "select * from userdata where username='" + username + "' and password='" + password + "'";

		return jdbctemplate.query(query, new RowMapper<Customer>() {
			public Customer mapRow(ResultSet rs, int row) throws SQLException {
				Customer customer = new Customer();

				customer.setUsername(rs.getString("username"));
				customer.setPassword(rs.getString("password"));
				customer.setUserid(rs.getInt("userid"));
				return customer;
			}
		});
	}

}
