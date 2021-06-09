package com.project;

import java.net.http.HttpRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class TripDao {
	
	
	static volatile int routeid1;
	private JdbcTemplate jdbctemplate;

	public void setJdbctemplate(JdbcTemplate jdbctemplate) {
	this.jdbctemplate = jdbctemplate;
	}
	
	public List<Trip> getAvailableSeats(int id){
		
		String query="select `Seats` from tripdata where routeID="+id;

Route route=new Route();
route.getRouteID();
		return jdbctemplate.query(query,new RowMapper<Trip>(){
		public Trip mapRow(ResultSet rs, int row) throws SQLException {
		Trip trip=new Trip();

		trip.setSeats(rs.getInt("Seats"));

		return trip;
		}
		});
		}
	
	public String getSeats(int id){
		routeid1= id;
		String sql = "select `Seats` from tripdata where routeID=?";
		String seat = jdbctemplate.queryForObject(sql, new Object[] { id }, String.class);
		return seat;
		}
	public List<Trip> CheckDate(String date, final int ticket){
		
		String query="select * from tripdata where Date=\""+date+"\" and routeID="+routeid1+";";
		String h;
		return jdbctemplate.query(query,new RowMapper<Trip>(){
		public Trip mapRow(ResultSet rs, int row) throws SQLException {
		
int vacantSeats=rs.getInt("Seats");
		
		if(ticket<vacantSeats) {
			Trip trip=new Trip();
			trip.setTripID(rs.getInt("trip_id"));
			return trip;
		}
		else {
			Trip trip=new Trip();
			return trip;
		}
		
		}
		});
		}

}


