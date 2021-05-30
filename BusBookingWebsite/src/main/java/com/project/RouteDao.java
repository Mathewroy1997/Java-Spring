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


public class RouteDao {
	
	private JdbcTemplate jdbctemplate;
	public void setJdbctemplate(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
		}
	
	public List<Route> getDeparture(){
		String query="select * from routedata;";


		return jdbctemplate.query(query,new RowMapper<Route>(){
		public Route mapRow(ResultSet rs, int row) throws SQLException {
		Route route=new Route();

		route.setDeparture(rs.getString("Departure"));
		//route.setD2(rs.getString(1));
		//route.setD3(rs.getString(1));
		//route.setD4(rs.getString(1));
		/*customer.setFirstname(rs.getString("firstname"));
		customer.setLastname(rs.getString("lastname"));
		customer.setEmail(rs.getString("email"));
		customer.setPhone(rs.getString("phone"));*/

		return route;
		}
		});
		}
	}
