package com.project;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
//import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
//import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.project.Customer;
public class CustomerDao {
private JdbcTemplate jdbctemplate;

public void setJdbctemplate(JdbcTemplate jdbctemplate) {
this.jdbctemplate = jdbctemplate;
}


public List<Customer> getData(String username,String password){
String query="select * from userdata where username='"+username+"' and password='"+password+"'";


return jdbctemplate.query(query,new RowMapper<Customer>(){
public Customer mapRow(ResultSet rs, int row) throws SQLException {
Customer customer=new Customer();

customer.setUsername(rs.getString("username"));
customer.setPassword(rs.getString("password"));
customer.setUserid(rs.getInt("userid"));
return customer;
}
});
}

public Boolean saveEmployeeByPreparedStatement(final Customer e){
String query="insert into userdata(username,password,firstname,lastname,email,address,phone) values(?,?,?,?,?,?,?)";
return jdbctemplate.execute(query,new PreparedStatementCallback<Boolean>(){
public Boolean doInPreparedStatement(PreparedStatement ps)
throws SQLException, DataAccessException {

ps.setString(1,e.getUsername());
ps.setString(2,e.getPassword());
ps.setString(3,e.getFirstname());
ps.setString(4,e.getLastname());
ps.setString(5,e.getEmail());
ps.setString(6,e.getAddress());
ps.setString(7,e.getPhone());
return ps.execute();

}
});}


public int setPassengerData(String name, int age, int id, int userid) {
	String sql="insert into passengerdata(userid,name,age,id) values("+userid+","+"'"+name+"'"+","+age+","+id+")";    
    return jdbctemplate.update(sql);
	
}


public List<Passenger> getPassengerData(int userid) {
		String  query="select * from passengerdata where userid='"+userid+"'"; return
			  jdbctemplate.query(query,new RowMapper<Passenger>(){
				  public Passenger mapRow(ResultSet rs, int row) throws SQLException {
					  Passenger passenger=new Passenger(); 
					  passenger.setUserid(rs.getInt("userid"));
					  passenger.setName(rs.getString("name"));
					  passenger.setAge(rs.getInt("age"));
					  passenger.setId(rs.getInt("id"));
			  return passenger;} }); 
	
}


public List<Temp> callData() {
	String  query="select * from temp "; return
			  jdbctemplate.query(query,new RowMapper<Temp>(){
				  public Temp mapRow(ResultSet rs, int row) throws SQLException {
					  Temp temp1=new Temp(); 
					  
					  temp1.setName(rs.getString("name"));
					  temp1.setAge(rs.getString("age"));
					  temp1.setId(rs.getString("id"));
			  return temp1;} });
}


public int saveToTemp(int userid,String date,int tripid,String name1, int age1, int id1) {
	String sql="insert into temppass(userid,date,tripid,name,age,id) values("+""+userid+",'"+date+"'"+","+tripid+",'"+name1+"'"+","+age1+","+id1+")";   
    return jdbctemplate.update(sql);
	
}


public List<TempPass> getFromtemp() {
	String  query="select * from temppass "; return
			  jdbctemplate.query(query,new RowMapper<TempPass>(){
				  public TempPass mapRow(ResultSet rs, int row) throws SQLException {
					  TempPass temp1=new TempPass(); 
					  
					  temp1.setName(rs.getString("name"));
					  temp1.setAge(rs.getInt("age"));
					  temp1.setId(rs.getInt("id"));
			  return temp1;} });
	
}


public int setViewData(ViewBookedData viewdata) {
	int userid=viewdata.userid;
	String date=viewdata.date;
	String departure=viewdata.departure;
	String destination=viewdata.destination;
	int tickets=viewdata.tickets;
	String sql="insert into viewdata(userid,date,departure,destination,tickets) values("+userid+","+"'"+date+"'"+",'"+departure+"'"+",'"+destination+"'"+","+tickets+")";
	return jdbctemplate.update(sql);
}


public List<ViewBookedData> setBookedHistory(int userid) {
	String  query="select * from viewdata where userid="+userid+""; return
			  jdbctemplate.query(query,new RowMapper<ViewBookedData>(){
				  public ViewBookedData mapRow(ResultSet rs, int row) throws SQLException {
					  ViewBookedData view1= new ViewBookedData();
				
					  view1.setDate(rs.getString("date"));
					  view1.setDeparture(rs.getString("departure"));
					  view1.setDestination(rs.getString("destination"));
					  view1.setTickets(rs.getInt("tickets"));
			  return view1;} }); 
	
}


public int setMasterTable(MasterPassengerTable master) {
	int userid=master.userid;
	String date=master.date;
	int tripid=master.tripid;
	String name=master.name;
	int age=master.age;
	int id=master.id;
	String sql="insert into masterpassenger(userid,date,tripid,name,age,id) values("+""+userid+",'"+date+"'"+","+tripid+",'"+name+"'"+","+age+","+id+")";   
    return jdbctemplate.update(sql);	
	
}


public int TruncateTemppass() {
	String sql="Truncate table temppass";   
    return jdbctemplate.update(sql);
	
}


public int ReduceSeats(int tickets, MasterPassengerTable master) {
	String sql="update tripdata set Seats=Seats-"+tickets+" where trip_id="+master.tripid;   
    return jdbctemplate.update(sql);	
	
}
}