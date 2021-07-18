package com.project.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;


import org.springframework.dao.DataAccessException;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import org.springframework.jdbc.core.RowMapper;

import com.project.models.CompleteBookingDetails;
import com.project.models.Customer;

public class CustomerDao {
	private JdbcTemplate jdbctemplate;

	public void setJdbctemplate(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
	}

	

	public Boolean addNewUser(final Customer e) {
		String query = "insert into userdata(username,password,firstname,lastname,email,address,phone) values(?,?,?,?,?,?,?)";
		return jdbctemplate.execute(query, new PreparedStatementCallback<Boolean>() {
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {

				ps.setString(1, e.getUsername());
				ps.setString(2, e.getPassword());
				ps.setString(3, e.getFirstname());
				ps.setString(4, e.getLastname());
				ps.setString(5, e.getEmail());
				ps.setString(6, e.getAddress());
				ps.setString(7, e.getPhone());
				return ps.execute();

			}
		});
	}

	public int setPassengerData(String name, int age, int id, int userid) {
		String sql = "insert into passengerdata(userid,name,age,id) values(" + userid + "," + "'" + name + "'" + ","
				+ age + "," + id + ")";
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

	public int moveDataFromTemporaryToMaster() {
		String sql = "INSERT INTO masterpassenger SELECT * FROM temporary_passengers;";
		return jdbctemplate.update(sql);
		
	}

	public List<CompleteBookingDetails> getUserBookingHistory(int userId) {
		String query = "select * from completebookingdata where userid=" + userId + "";
		return jdbctemplate.query(query, new RowMapper<CompleteBookingDetails>() {
			public CompleteBookingDetails mapRow(ResultSet rs, int row) throws SQLException {
				CompleteBookingDetails userBookingHistory = new CompleteBookingDetails();
				
				userBookingHistory.setUserId(rs.getInt("userId"));
				userBookingHistory.setDate(rs.getString("date"));
				userBookingHistory.setRouteId(rs.getInt("routeId"));
				userBookingHistory.setDeparture(rs.getString("departure"));
				userBookingHistory.setDestination(rs.getString("destination"));
				userBookingHistory.setTripId(rs.getInt("tripId"));
				userBookingHistory.setBusId(rs.getInt("busId"));
				userBookingHistory.setBusType(rs.getString("busType"));
				userBookingHistory.setPassengerName(rs.getString("name"));
				userBookingHistory.setPassengerAge(rs.getInt("age"));
				userBookingHistory.setPassengerId(rs.getString("id"));
				return userBookingHistory;
			}
		});
	}
}