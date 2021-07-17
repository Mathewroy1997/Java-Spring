package com.project.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.project.models.BusDetails;
import com.project.models.Route;
import com.project.models.RouteDetails;
import com.project.models.TripDetails;

public class BookingDao {
	
	private JdbcTemplate jdbctemplate;
	public void setJdbctemplate(JdbcTemplate jdbctemplate1) {
		this.jdbctemplate = jdbctemplate1;
		}

	public List<RouteDetails> getDeparture(){
		String query="select * from routedetails";
		

		return jdbctemplate.query(query,new RowMapper<RouteDetails>(){
		public RouteDetails mapRow(ResultSet resultSet, int row) throws SQLException {
			RouteDetails routeDetails= new RouteDetails();
				 routeDetails.setDeparture(resultSet.getString("departure"));
			
			

		return routeDetails;
		}
		});
		}

	public List<RouteDetails> getDestination(){
		String query="select * from routedetails;";


		return jdbctemplate.query(query,new RowMapper<RouteDetails>(){
		public RouteDetails mapRow(ResultSet resultSet, int row) throws SQLException {
		
			RouteDetails routeDetails=new RouteDetails();	
		routeDetails.setDestination(resultSet.getString("Destination"));
		
		return routeDetails;
		}
		});
		}

	public List<RouteDetails> getRouteDetails(String departure,String destination){
			
			String query3="select * from routedetails where Departure='"+departure+"' and Destination='"+destination+"';";
			return jdbctemplate.query(query3,new RowMapper<RouteDetails>(){
				public RouteDetails mapRow(ResultSet resultSet, int row) throws SQLException {
					RouteDetails routeDetails=new RouteDetails();
					
						routeDetails.setRouteId(resultSet.getInt("routeId"));
						 routeDetails.setDeparture(resultSet.getString("departure"));
						 routeDetails.setDestination(resultSet.getString("destination"));
						 
						 routeDetails.setTotalDistanceInKm((resultSet.getInt("totalKm")));
						
				
					
				
				return routeDetails;
				}
				});
	
	}

	public List<TripDetails> getTripDetails(int routeId, String date) {
		String query="select * from tripdetails where routeId='" + routeId + "' and date='"+date+"'";
		return jdbctemplate.query(query,new RowMapper<TripDetails>(){
			public TripDetails mapRow(ResultSet resultSet, int row) throws SQLException {
				TripDetails tripDetails=new TripDetails();
				
					tripDetails.setTripId(resultSet.getInt("tripId"));
					 tripDetails.setBusId(resultSet.getInt("busId"));
					 tripDetails.setAvailableSeats(resultSet.getInt("seatsAvailable"));
					 tripDetails.setDate(resultSet.getString("date"));
					 tripDetails.setRouteId(resultSet.getInt("routeId"));
					 
					 
					
			
				
			
			return tripDetails;
			}
			});
	}

	

	public List<BusDetails> getBusDetails(int busId) {
		String query="select * from busdetails where busId='" + busId + "'";
		return jdbctemplate.query(query,new RowMapper<BusDetails>(){
			public BusDetails mapRow(ResultSet resultSet, int row) throws SQLException {
				BusDetails busDetails=new BusDetails();
				
					busDetails.setBusId(resultSet.getInt("busId"));
					busDetails.setBusType(resultSet.getString("category"));
					 busDetails.setBusRatePerKm(resultSet.getInt("rate/km"));
					 busDetails.setBusTotalSeats(resultSet.getInt("seats"));
					 
					 
					 
					
			
				
			
			return busDetails;
			}
			});
	}

	public int setPassengerDetialisToTemperoryTable(int userId, String date, int routeId, int tripId,
			String passengerName, String passengerAge, String passengerId) {
		String sql = "insert into temporary_passengersdetails(userid,date,routeId,tripId,name,age,id) values(" + userId + "," + "'" + date + "'," + ""+routeId+","+"" + tripId + ","
				+ "'" + passengerName + "'," + "'" + passengerAge + "'," +"" +passengerId + ")";
		return jdbctemplate.update(sql);
		
	}

	

	

	

}
