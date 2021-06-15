package com.project;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.project.Customer;
import com.project.Route;

public class RouteDao {
	int pricePerTicket;
	int trial;
	int math;
	public void setPricePerTicket(int pricePerTicket) {
		this.pricePerTicket=pricePerTicket;
	}
	public int getPricePerTicket() {
		return pricePerTicket;
	}
	
	
	private JdbcTemplate jdbctemplate1;
	public void setJdbctemplate(JdbcTemplate jdbctemplate1) {
		this.jdbctemplate1 = jdbctemplate1;
		}
	
	public List<Route> getDeparture(){
		String query="select * from routedata";
		

		return jdbctemplate1.query(query,new RowMapper<Route>(){
		public Route mapRow(ResultSet rs, int row) throws SQLException {
			Route route= new Route();
				 route.setDeparture(rs.getString("Departure"));
			
			

		return route;
		}
		});
		}
		public List<Route> getDestination(){
		String query="select * from routedata;";


		return jdbctemplate1.query(query,new RowMapper<Route>(){
		public Route mapRow(ResultSet rs, int row) throws SQLException {
		
			Route route1=new Route();	
		route1.setDestination(rs.getString("Destination"));
		
		return route1;
		}
		});
		}
		
		public List<Route> getRouteData(String departure,String destination){
			
			String query3="select * from routedata where Departure='"+departure+"' and Destination='"+destination+"';";
			return jdbctemplate1.query(query3,new RowMapper<Route>(){
				public Route mapRow(ResultSet rs, int row) throws SQLException {
					Route route3=new Route();
					
						 route3.setDeparture(rs.getString("Departure"));
						 route3.setDestination(rs.getString("Destination"));
						 route3.setRouteID(rs.getInt("route id"));
						 route3.setRate(rs.getInt("Rate"));
						int trial=route3.rate;
						RouteDao dao1=new RouteDao();
						dao1.setPricePerTicket(trial);
			
						
						 trial=route3.routeID;
						 math=route3.routeID;
					
				
				return route3;
				}
				});
	
	}
		public int totalPrice(int tickets, int rate) { 

			int totalPrice=tickets*rate;
			return totalPrice;
		}
		
}
