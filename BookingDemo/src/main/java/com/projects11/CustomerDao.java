package com.projects11;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.projects11.Customer;
public class CustomerDao {
	private JdbcTemplate jdbctemplate;
	public void setJdbctemplate(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
	}
	
	public List<Customer> getData(String username,String password){
		String query="select * from users where username='"+username+"' and password='"+password+"'";
	return jdbctemplate.query(query,new RowMapper<Customer>(){ 
        public Customer mapRow(ResultSet rs, int row) throws SQLException {    
            Customer customer=new Customer();
            customer.setUsername(rs.getString(1));
    		customer.setPassword(rs.getString(2));
    		customer.setFirstname(rs.getString(3));
    		customer.setLastname(rs.getString(4));
    		customer.setEmail(rs.getString(5));
    		customer.setPhone(rs.getString(6));
    		customer.setCustomer_id(rs.getInt(7));
    		return customer;}
        });    
}    
}