package com.project.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.Catch;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
//import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.project.models.Customer;
import com.project.models.MasterPassengerTable;
import com.project.models.Passenger;
import com.project.models.Temp;
import com.project.models.TemperoryPassenger;
import com.project.models.Trip;
import com.project.models.ViewBookedData;

public class CustomerDao {
	private JdbcTemplate jdbctemplate;

	public void setJdbctemplate(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
	}

	public List<Customer> getData(String username, String password) {
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

	public List<Passenger> getPassengerData(int userid) {
		String query = "select * from passengerdata where userid='" + userid + "'";
		return jdbctemplate.query(query, new RowMapper<Passenger>() {
			public Passenger mapRow(ResultSet rs, int row) throws SQLException {
				Passenger passenger = new Passenger();
				passenger.setUserid(rs.getInt("userid"));
				passenger.setName(rs.getString("name"));
				passenger.setAge(rs.getInt("age"));
				passenger.setId(rs.getInt("id"));
				return passenger;
			}
		});

	}

	public List<Temp> callData() {
		String query = "select * from temp ";
		return jdbctemplate.query(query, new RowMapper<Temp>() {
			public Temp mapRow(ResultSet rs, int row) throws SQLException {
				Temp temp1 = new Temp();

				temp1.setName(rs.getString("name"));
				temp1.setAge(rs.getString("age"));
				temp1.setId(rs.getString("id"));
				return temp1;
			}
		});
	}

	public int saveToTemporaryPassengers(int userid, String date, int tripid, String name1, int age1, int id1) {
		String sql = "insert into temporary_passengers(userid,date,tripid,name,age,id) values(" + "" + userid + ",'" + date + "'"
				+ "," + tripid + ",'" + name1 + "'" + "," + age1 + "," + id1 + ")";
		return jdbctemplate.update(sql);

	}

	public List<TemperoryPassenger> getFromTemporaryPassenger() {
		String query = "select * from temporary_passengers ";
		return jdbctemplate.query(query, new RowMapper<TemperoryPassenger>() {
			public TemperoryPassenger mapRow(ResultSet rs, int row) throws SQLException {
				TemperoryPassenger passenger = new TemperoryPassenger();

				passenger.setName(rs.getString("name"));
				passenger.setAge(rs.getInt("age"));
				passenger.setId(rs.getInt("id"));
				return passenger;
			}
		});

	}

	public int setViewData(ViewBookedData viewdata) {
		int userid = viewdata.userid;
		String date = viewdata.date;
		String departure = viewdata.departure;
		String destination = viewdata.destination;
		int tickets = viewdata.tickets;
		String sql = "insert into viewdata(userid,date,departure,destination,tickets) values(" + userid + "," + "'"
				+ date + "'" + ",'" + departure + "'" + ",'" + destination + "'" + "," + tickets + ")";
		return jdbctemplate.update(sql);
	}

	public List<ViewBookedData> setBookedHistory(int userid) {
		String query = "select * from viewdata where userid=" + userid + "";
		return jdbctemplate.query(query, new RowMapper<ViewBookedData>() {
			public ViewBookedData mapRow(ResultSet rs, int row) throws SQLException {
				ViewBookedData view1 = new ViewBookedData();

				view1.setDate(rs.getString("date"));
				view1.setDeparture(rs.getString("departure"));
				view1.setDestination(rs.getString("destination"));
				view1.setTickets(rs.getInt("tickets"));
				return view1;
			}
		});

	}

	public int setMasterTable(MasterPassengerTable master) {
		int userid = master.userid;
		String date = master.date;
		int tripid = master.tripid;
		String name = master.name;
		int age = master.age;
		int id = master.id;
		String sql = "insert into masterpassenger(userid,date,tripid,name,age,id) values(" + "" + userid + ",'" + date
				+ "'" + "," + tripid + ",'" + name + "'" + "," + age + "," + id + ")";
		return jdbctemplate.update(sql);

	}

	public List<MasterPassengerTable> getFromMasterPassenger() {
		return jdbctemplate.query("select * from  masterpassenger", new RowMapper<MasterPassengerTable>() {
			public MasterPassengerTable mapRow(ResultSet rs, int row) throws SQLException {
				MasterPassengerTable details = new MasterPassengerTable();
				details.setUserid(rs.getInt("userid"));
				details.setDate(rs.getString("date"));
				details.setTripid(rs.getInt("tripid"));
				details.setName(rs.getString("name"));
				details.setAge(rs.getInt("age"));
				details.setId(rs.getInt("id"));
				return details;
			}
		});
	}

	public int TruncateTemporaryTable() {
		String sql = "Truncate table temporary_passengers";
		return jdbctemplate.update(sql);

	}

	public int ReduceSeats(int tickets, int tripId, String date) {
		
		String sql = "update tripdata set Seats=Seats-" + tickets + " where trip_id=" +tripId + " and date='"
				+ date + "';";
		return jdbctemplate.update(sql);

	}

	public int deleteRoute(int routeid) throws DataIntegrityViolationException {
		String sql = "delete from routedata where routeid=" + routeid;
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

	public int deleteTrip(int tripid) {
		String sql = "delete from tripdata where trip_id=" + tripid;
		return jdbctemplate.update(sql);

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
}