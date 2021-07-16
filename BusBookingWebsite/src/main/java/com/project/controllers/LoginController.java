package com.project.controllers;

import java.net.http.HttpRequest;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.project.*;
import com.project.dao.AdminDao;
import com.project.dao.CustomerDao;
import com.project.dao.RouteDao;
import com.project.dao.TripDao;
import com.project.models.Customer;
import com.project.models.MasterPassengerTable;
import com.project.models.Passenger;
import com.project.models.Route;
import com.project.models.Temp;
import com.project.models.TemperoryPassenger;
import com.project.models.Trip;
import com.project.models.ViewBookedData;
import com.project.service.CustomerService;
import com.project.*;

@Controller
public class LoginController {
	
	
	
	@Autowired
	CustomerDao customerDao;

	@Autowired
	RouteDao routeDao;

	@Autowired
	TripDao tripDao;

	@Autowired
	AdminDao adminDao;

	@Autowired
	CustomerService serviceClass;

	@RequestMapping(value = "/login", method = RequestMethod.POST)

	public String login(HttpServletRequest request, Model m) {

		String username = request.getParameter("username");

		String password = request.getParameter("password");

		boolean myCheckBox = request.getParameter("adminCheck") != null;

		if (myCheckBox == true) {

			int flag = serviceClass.adminLogin(username, password);

			if (flag == 0) {
				String error = "Invalid Credentials";

				m.addAttribute("error", error);

				return "invalidLoginAttempt";
			} else {


				return "adminHome";
			}

		}
		int flag = serviceClass.login(username, password);

		List<Customer> userIDlist = customerDao.getData(username, password);

		if (flag == 0) {
			String error = "Invalid Credentials";

			m.addAttribute("error", error);

			return "invalidLoginAttempt";
		} 
		/*
			 * else if (flag == 1) { return "adminHome";
			 * 
			 * }
			 */ 
		else {
			
			Customer customer;
			customer = userIDlist.get(0);
			m.addAttribute("username", username);
			m.addAttribute("userId",customer.getUserid());
			m.addAttribute("customer",customer);
			return "welcome";
		}
	}

	@RequestMapping("signUp")
	public String registrationPage() {
		return "Register";
	}

	@RequestMapping(value="logout")
	public String backToIndex() {
		return "index";
	}

	


	
}
