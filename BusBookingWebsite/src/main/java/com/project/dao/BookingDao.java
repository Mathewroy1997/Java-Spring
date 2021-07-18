package com.project.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;

import com.project.models.BusDetails;
import com.project.models.Route;
import com.project.models.RouteDetails;
import com.project.models.TemperoryPassengersDetails;
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
					 busDetails.setBusRatePerKm(resultSet.getInt("ratePerKm"));
					 busDetails.setBusTotalSeats(resultSet.getInt("seats"));
					 
					 
					 
					
			
				
			
			return busDetails;
			}
			});
	}

	public Boolean setPassengerDetialisToTemperoryTable(TemperoryPassengersDetails allBookingData) {
		String query="insert into temporary_passengersdetails(userId,date,routeId,departure,destination,tripId,busId,busType,name,age,id) values(?,?,?,?,?,?,?,?,?,?,?)";
		return jdbctemplate.execute(query,new PreparedStatementCallback<Boolean>(){
		public Boolean doInPreparedStatement(PreparedStatement ps)
		throws SQLException, DataAccessException {


		ps.setInt(1,allBookingData.getUserId());
		ps.setString(2,allBookingData.getDate());
		ps.setInt(3,allBookingData.getRouteId());
		ps.setString(4,allBookingData.getDeparture());
		ps.setString(5,allBookingData.getDestination());
		ps.setInt(6,allBookingData.getTripId());
		ps.setInt(7,allBookingData.getBusId());
		ps.setString(8,allBookingData.getBusType());
		ps.setString(9,allBookingData.getPassengerName());
		ps.setInt(10,allBookingData.getPassengerAge());
		ps.setString(11,allBookingData.getPassengerId());
		
		return ps.execute();

		}
		});
		
	}

	public List<TemperoryPassengersDetails> getTemperoryPassengersList() {
		String query="select * from temporary_passengersdetails";
		return jdbctemplate.query(query,new RowMapper<TemperoryPassengersDetails>(){
			public TemperoryPassengersDetails mapRow(ResultSet resultSet, int row) throws SQLException {
				TemperoryPassengersDetails passenger=new TemperoryPassengersDetails();
				
					passenger.setPassengerName(resultSet.getString("name"));
					passenger.setPassengerAge(resultSet.getInt("age"));
					passenger.setPassengerId(resultSet.getString("id"));
					 
					 
					 
					
			
				
			
			return passenger;
			}
			});
	}

	public int reduceSeatsInTripTable(int tripId, int userTickets) {
		String sql = "update tripdetails set seatsAvailable=seatsAvailable-" + userTickets + " where tripId=" +tripId + " ;";
		return jdbctemplate.update(sql);
		
	}

	public int movePassengerTemperoryDetailsToPermanaent() {
		String sql = "INSERT INTO completebookingdata (userId,date,routeId,departure,destination,tripId,busId,busType,name,age,id) SELECT userId,date,routeId,departure,destination,tripId,busId,busType,name,age,id FROM temporary_passengersdetails";
		return jdbctemplate.update(sql);
		
	}

	public int TruncateTemperoryPassengerTable() {
		String sql = "Truncate table temporary_passengersdetails";
		return jdbctemplate.update(sql);
		
	}

	

	

	

}
