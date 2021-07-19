package com.project.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;

import com.project.models.AdminData;
import com.project.models.BusCategory;
import com.project.models.BusDetails;
import com.project.models.CompleteBookingDetails;
import com.project.models.Customer;

import com.project.models.RouteDetails;

import com.project.models.TripDetails;

public class AdminDao {
	private JdbcTemplate jdbctemplate;

	public void setJdbctemplate(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
	}

	public int insertNewAdmin(String newAdminName, String newUsername, String newPassword) {
		String sql = "insert into admindata(AdminName,Username,Password) values(" + "'" + newAdminName + "'" + ",'"
				+ newUsername + "'" + ",'" + newPassword + "')";
		return jdbctemplate.update(sql);

	}

	public List<AdminData> getAllAdminData() {
		String query = "select * from admindata ";

		return jdbctemplate.query(query, new RowMapper<AdminData>() {
			public AdminData mapRow(ResultSet rs, int row) throws SQLException {
				AdminData adminData = new AdminData();
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

	public int addNewAdmin(String newAdminName, String newUsername, String newPassword) {
		String sql = "insert into admindata(AdminName,Username,Password) values(" + "'" + newAdminName + "'" + ",'"
				+ newUsername + "'" + ",'" + newPassword + "')";
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
		String query = "select * from busdetails";
		return jdbctemplate.query(query, new RowMapper<BusDetails>() {
			public BusDetails mapRow(ResultSet resultSet, int row) throws SQLException {
				BusDetails bus = new BusDetails();

				bus.setBusId(resultSet.getInt("busId"));
				bus.setBusType(resultSet.getString("category"));
				bus.setBusRatePerKm(resultSet.getInt("ratePerKm"));
				bus.setBusTotalSeats(resultSet.getInt("seats"));

				return bus;
			}
		});
	}

	public Boolean addNewBus(BusDetails bus) {
		String query = "insert into busdetails(category,ratePerKm,seats) values(?,?,?)";
		return jdbctemplate.execute(query, new PreparedStatementCallback<Boolean>() {
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {

				ps.setString(1, bus.getBusType());
				ps.setInt(2, bus.getBusRatePerKm());
				ps.setInt(3, bus.getBusTotalSeats());

				return ps.execute();

			}
		});

	}

	public List<BusCategory> getBusCategories() {
		String query = "select * from buscategories";
		return jdbctemplate.query(query, new RowMapper<BusCategory>() {
			public BusCategory mapRow(ResultSet resultSet, int row) throws SQLException {
				BusCategory busCategory = new BusCategory();

				busCategory.setBusCategory(resultSet.getString("busCategory"));

				return busCategory;
			}
		});
	}

	public int addNewBusCategory(String category) {
		String sql = "insert into buscategories(busCategory) values(" + "'" + category + "')";
		return jdbctemplate.update(sql);

	}

	public int deleteBus(int busId) {
		String sql = "delete from busdetails where busId=" + busId;
		return jdbctemplate.update(sql);

	}

	public int changeBusDetails(int busId, int ratePerKm, int seats) {
		String sql = "UPDATE busdetails SET `ratePerKm` = " + ratePerKm + ", `seats` = " + seats + " WHERE (`busId` = "
				+ busId + ");";
		return jdbctemplate.update(sql);

	}

	public List<RouteDetails> getRouteDetails() {
		String query = "select * from routedetails";
		return jdbctemplate.query(query, new RowMapper<RouteDetails>() {
			public RouteDetails mapRow(ResultSet rs, int row) throws SQLException {
				RouteDetails route = new RouteDetails();

				route.setDeparture(rs.getString("departure"));
				route.setDestination(rs.getString("destination"));
				route.setRouteId(rs.getInt("routeId"));
				route.setTotalDistanceInKm(rs.getInt("totalKm"));
				;

				return route;
			}
		});
	}

	public int deleteRouteDetails(int routeId) {
		String sql = "delete from routedetails where routeId=" + routeId;
		return jdbctemplate.update(sql);

	}

	public Boolean setNewRouteDetails(RouteDetails route) {
		String query = "insert into routedetails(departure,destination,totalKm) values(?,?,?)";
		return jdbctemplate.execute(query, new PreparedStatementCallback<Boolean>() {
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {

				ps.setString(1, route.getDeparture());
				ps.setString(2, route.getDestination());
				ps.setInt(3, route.getTotalDistanceInKm());

				return ps.execute();

			}
		});
	}

	public List<TripDetails> getTripDetails() {
		String query = "select * from tripdetails";
		return jdbctemplate.query(query, new RowMapper<TripDetails>() {
			public TripDetails mapRow(ResultSet resultSet, int row) throws SQLException {

				TripDetails trip = new TripDetails();

				trip.setTripId(resultSet.getInt("tripId"));
				trip.setDate(resultSet.getString("date"));
				trip.setRouteId(resultSet.getInt("routeId"));
				trip.setBusId(resultSet.getInt("busId"));
				trip.setAvailableSeats(resultSet.getInt("seatsAvailable"));

				return trip;
			}
		});
	}

	public Boolean addNewTripDetails(TripDetails trip) {
		String query = "insert into tripdetails(date,routeId,busId,seatsAvailable) values(?,?,?,?)";
		return jdbctemplate.execute(query, new PreparedStatementCallback<Boolean>() {
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {

				ps.setString(1, trip.getDate());
				ps.setInt(2, trip.getRouteId());
				ps.setInt(3, trip.getBusId());
				ps.setInt(4, trip.getAvailableSeats());

				return ps.execute();

			}
		});

	}

	public int deleteTripDetails(int tripId) {
		String sql = "delete from tripdetails where tripId=" + tripId;
		return jdbctemplate.update(sql);

	}

	public List<CompleteBookingDetails> getBookingHistory() {
		String query = "select * from completebookingdata ";
		return jdbctemplate.query(query, new RowMapper<CompleteBookingDetails>() {
			public CompleteBookingDetails mapRow(ResultSet rs, int row) throws SQLException {
				CompleteBookingDetails adminBookingHistory = new CompleteBookingDetails();

				adminBookingHistory.setUserId(rs.getInt("userId"));
				adminBookingHistory.setDate(rs.getString("date"));
				adminBookingHistory.setRouteId(rs.getInt("routeId"));
				adminBookingHistory.setDeparture(rs.getString("departure"));
				adminBookingHistory.setDestination(rs.getString("destination"));
				adminBookingHistory.setTripId(rs.getInt("tripId"));
				adminBookingHistory.setBusId(rs.getInt("busId"));
				adminBookingHistory.setBusType(rs.getString("busType"));
				adminBookingHistory.setPassengerName(rs.getString("name"));
				adminBookingHistory.setPassengerAge(rs.getInt("age"));
				adminBookingHistory.setPassengerId(rs.getString("id"));
				return adminBookingHistory;
			}
		});
	}
}
