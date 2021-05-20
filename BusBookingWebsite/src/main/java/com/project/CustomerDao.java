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
String query="select * from data where username='"+username+"' and password='"+password+"'";


return jdbctemplate.query(query,new RowMapper<Customer>(){
public Customer mapRow(ResultSet rs, int row) throws SQLException {
Customer customer=new Customer();

customer.setUsername(rs.getString("username"));
customer.setPassword(rs.getString("password"));
/*customer.setFirstname(rs.getString("firstname"));
customer.setLastname(rs.getString("lastname"));
customer.setEmail(rs.getString("email"));
customer.setPhone(rs.getString("phone"));*/

return customer;
}
});
}

public Boolean saveEmployeeByPreparedStatement(final Customer e){
String query="insert into data(username,password,firstname,lastname,email,address,phone) values(?,?,?,?,?,?,?)";
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
});}}