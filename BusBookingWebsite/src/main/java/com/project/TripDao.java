package com.project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class TripDao {
	
	
	private JdbcTemplate jdbctemplate;

	public void setJdbctemplate(JdbcTemplate jdbctemplate) {
	this.jdbctemplate = jdbctemplate;
	}
	
	public List<Trip> getAvailableSeats(){
		String query="select `Seats` from tripdata where routeID=101;";


		return jdbctemplate.query(query,new RowMapper<Trip>(){
		public Trip mapRow(ResultSet rs, int row) throws SQLException {
		Trip trip=new Trip();

		trip.setSeats(rs.getInt("Seats"));

		return trip;
		}
		});
		}

}
