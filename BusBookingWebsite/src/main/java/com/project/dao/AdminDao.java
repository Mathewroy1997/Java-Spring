package com.project.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;

import com.project.models.AdminData;
import com.project.models.BusCategory;
import com.project.models.BusDetails;
import com.project.models.Customer;
import com.project.models.Route;
import com.project.models.Trip;

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
	public List<AdminData> getAllAdminData() {
		String query = "select * from admindata ";

		return jdbctemplate.query(query, new RowMapper<AdminData>() {
			public AdminData mapRow(ResultSet rs, int row) throws SQLException {
				AdminData adminData=new AdminData();
				adminData.setAdminID(rs.getInt("AdminID"));
				adminData.setAdminName(rs.getString("AdminName"));
				adminData.setUsername(rs.getString("Username"));
				return adminData;
			}
		});
	}
	public int deleteUser(int adminID) {
		String sql = "delete from admindata where AdminID=" + adminID;
		return jdbctemplate.update(sql);
		
	}
	public List<Route> getRouteTable() {
		String query3="select * from routedata";
		return jdbctemplate.query(query3,new RowMapper<Route>(){
			public Route mapRow(ResultSet rs, int row) throws SQLException {
				Route route=new Route();
				
					 route.setDeparture(rs.getString("Departure"));
					 route.setDestination(rs.getString("Destination"));
					 route.setRouteID(rs.getInt("routeid"));
					 route.setRate(rs.getInt("Rate"));

				
			
			return route;
			}
			});
	}
	public Boolean addNewRoute(final Route newRoute) {
			String query="insert into routedata(routeid,Departure,Destination,Rate) values(?,?,?,?)";
			return jdbctemplate.execute(query,new PreparedStatementCallback<Boolean>(){
			public Boolean doInPreparedStatement(PreparedStatement ps)
			throws SQLException, DataAccessException {

			ps.setInt(1,newRoute.getRouteID());
			ps.setString(2,newRoute.getDeparture());
			ps.setString(3,newRoute.getDestination());
			ps.setInt(4,newRoute.getRate());
			
			return ps.execute();

			}
			});
			
		}
	public int deleteRoute(int routeId) throws DataIntegrityViolationException {
		String sql = "delete from routedata where routeid=" + routeId;
		return jdbctemplate.update(sql);

	}
	public List<Trip> getTripData() {
		String query = "select * from tripdata";
		return jdbctemplate.query(query, new RowMapper<Trip>() {
			public Trip mapRow(ResultSet rs, int row) throws SQLException {
				Trip trip = new Trip();
				trip.setDate(rs.getString("date"));
				trip.setSeats(rs.getInt("Seats"));
				trip.setRouteID(rs.getInt("routeID"));
				trip.setTripID(rs.getInt("trip_id"));

				return trip;
			}
		});

	}
	public Boolean addNewTrip(final Trip trip) {
		String query = "insert into tripdata(trip_id,date,routeID,Seats) values(?,?,?,?)";
		return jdbctemplate.execute(query, new PreparedStatementCallback<Boolean>() {
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {

				ps.setInt(1, trip.getTripID());
				ps.setString(2, trip.getDate());
				ps.setInt(3, trip.getRouteID());
				ps.setInt(4, trip.getSeats());

				return ps.execute();

			}
		});

	}
	public int addNewAdmin(String newAdminName,String newUsername,String newPassword) {
		String sql="insert into admindata(AdminName,Username,Password) values("+"'"+newAdminName+"'"+",'"+newUsername+"'"+",'"+newPassword+"')";   
	    return jdbctemplate.update(sql);
		
	}
	public List<Customer> getAllUserData() {
		String query = "select * from userdata ";

		return jdbctemplate.query(query, new RowMapper<Customer>() {
			public Customer mapRow(ResultSet rs, int row) throws SQLException {
				Customer customer = new Customer();
				customer.setUserid(rs.getInt("userid"));
				customer.setUsername(rs.getString("username"));
				customer.setPassword(rs.getString("password"));
				customer.setFirstname(rs.getString("firstname"));
				customer.setLastname(rs.getString("lastname"));
				customer.setEmail(rs.getString("email"));
				customer.setAddress(rs.getString("address"));
				customer.setPhone(rs.getString("phone"));
				return customer;
			}
		});
	}
	public int deleteCustomer(int userId) {
		String sql = "delete from userdata where userid=" + userId;
		return jdbctemplate.update(sql);

	}
	public Boolean addNewUserFromAdmin(final Customer customer) {
		String query = "insert into userdata(username,firstname,lastname,email,address,phone,password) values(?,?,?,?,?,?,?)";
		return jdbctemplate.execute(query, new PreparedStatementCallback<Boolean>() {
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {

				ps.setString(1, customer.getUsername());
				ps.setString(2, customer.getFirstname());
				ps.setString(3, customer.getLastname());
				ps.setString(4, customer.getEmail());
				ps.setString(5, customer.getAddress());
				ps.setString(6, customer.getEmail());
				ps.setString(7, customer.getPassword());
				return ps.execute();

			}
		});

	}
	public List<BusDetails> getBusDetails() {
		String query="select * from busdetails";
		return jdbctemplate.query(query,new RowMapper<BusDetails>(){
			public BusDetails mapRow(ResultSet resultSet, int row) throws SQLException {
				BusDetails bus=new BusDetails();
				
					 bus.setBusId(resultSet.getInt("busId"));
					 bus.setBusType(resultSet.getString("category"));
					 bus.setBusRatePerKm(resultSet.getInt("ratePerKm"));
					 bus.setBusTotalSeats(resultSet.getInt("seats"));

				
			
			return bus;
			}
			});
	}
	public Boolean addNewBus(BusDetails bus) {
		String query="insert into busdetails(category,ratePerKm,seats) values(?,?,?)";
		return jdbctemplate.execute(query,new PreparedStatementCallback<Boolean>(){
		public Boolean doInPreparedStatement(PreparedStatement ps)
		throws SQLException, DataAccessException {

		ps.setString(1,bus.getBusType());
		ps.setInt(2,bus.getBusRatePerKm());
		ps.setInt(3,bus.getBusTotalSeats());
		
		
		return ps.execute();

		}
		});
		
	}
	public List<BusCategory> getBusCategories() {
		String query="select * from buscategories";
		return jdbctemplate.query(query,new RowMapper<BusCategory>(){
			public BusCategory mapRow(ResultSet resultSet, int row) throws SQLException {
				BusCategory busCategory=new BusCategory();
				
					
					 busCategory.setBusCategory(resultSet.getString("busCategory"));
					 

				
			
			return busCategory;
			}
			});
	}
	public int addNewBusCategory(String category) {
		String sql="insert into buscategories(busCategory) values("+"'"+category+"')";   
	    return jdbctemplate.update(sql);
		
	}
	public int deleteBus(int busId) {
		String sql = "delete from busdetails where busId=" + busId;
		return jdbctemplate.update(sql);

		
	}
	public int changeBusDetails(int busId, int ratePerKm, int seats) {
		String sql = "UPDATE busdetails SET `ratePerKm` = "+ratePerKm+", `seats` = "+seats+" WHERE (`busId` = "+busId+");" ;
		return jdbctemplate.update(sql);
		
	}
}
