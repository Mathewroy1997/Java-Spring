package com.project.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.project.models.CompleteBookingDetails;

public class ApiDao {
	private JdbcTemplate jdbctemplate;
	public void setJdbctemplate(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
		}
	
	public List<CompleteBookingDetails> getBookingDetails() {
		return jdbctemplate.query("select * from  completebookingdata", new RowMapper<CompleteBookingDetails>() {
			public CompleteBookingDetails mapRow(ResultSet rs, int row) throws SQLException {
				CompleteBookingDetails details = new CompleteBookingDetails();
				details.setUserId(rs.getInt("userid"));
				details.setDate(rs.getString("date"));
				details.setTripId(rs.getInt("tripid"));
				details.setPassengerName(rs.getString("name"));
				details.setPassengerAge(rs.getInt("age"));
				details.setPassengerId(rs.getString("id"));
				return details;
			}
		});
	}



}
