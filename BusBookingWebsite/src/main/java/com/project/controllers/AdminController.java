package com.project.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.dao.AdminDao;
import com.project.dao.CustomerDao;
import com.project.dao.RouteDao;
import com.project.dao.TripDao;
import com.project.models.AdminData;
import com.project.models.Customer;
import com.project.models.Route;
import com.project.models.Trip;
import com.project.service.AdminService;
import com.project.service.CustomerService;

@Controller
public class AdminController {
	
	Route route = new Route();
	Trip trip = new Trip();
	
	@Autowired
	CustomerDao customerDao;

	@Autowired
	RouteDao routeDao;

	@Autowired
	TripDao tripDao;

	@Autowired
	AdminDao adminDao;

	@Autowired
	CustomerService customerService;
	
	@Autowired
	AdminService adminService;
	
	@RequestMapping("/updateRoute")
	public String updateRoute(Model m) {
		List<Route> routeTable = routeDao.getRouteTable();
		route = routeTable.get(0);
		m.addAttribute("routeTable", routeTable);

		return "modifyRoute";
	}

	@RequestMapping("/addRoute")
	public String addNewRoute(HttpServletRequest request, Model m) {
		Route newRoute = new Route();
		newRoute.setRouteID(Integer.parseInt(request.getParameter("routeid")));
		newRoute.setDeparture(request.getParameter("departure"));
		newRoute.setDestination(request.getParameter("destination"));
		newRoute.setRate(Integer.parseInt(request.getParameter("rate")));

		routeDao.addNewRoute(newRoute);
		return "redirect:/updateRoute";

	}

	@RequestMapping("route/{route3.routeID}")
	public String deleteRoute(HttpServletRequest request) {

		String path = request.getServletPath();  

		int routeid = Integer.parseInt(path.replaceAll("[\\D]", ""));
		Model m;

		try {
			customerDao.deleteRoute(routeid);
			return "redirect:/updateRoute";
		} catch (DataIntegrityViolationException e) {
			return "admin_routeidexception";
		}

	}

	@RequestMapping("/backToRouteIDUpdation")
	public String returnToRouteIDUpdation() {
		return "redirect:/updateRoute";
	}

	@RequestMapping("updateTrip")
	public String updateTrip(Model m) {

		List<Trip> tripTable = customerDao.getTripData();
		trip = tripTable.get(0);
		m.addAttribute("tripTable", tripTable);
		return "admin_modifyTrip";
	}

	@RequestMapping("trip/{trip.tripID}")
	public String deleteTrip(HttpServletRequest request) {
		String path = request.getServletPath();
		int tripid = Integer.parseInt(path.replaceAll("[\\D]", ""));
		customerDao.deleteTrip(tripid);
		return "redirect:/updateTrip";
	}

	@RequestMapping("addTrip")
	public String addNewTrip(HttpServletRequest request, Model m) {
		Trip trip = new Trip();
		trip.setTripID(Integer.parseInt(request.getParameter("tripid")));
		trip.setDate(request.getParameter("date"));
		trip.setRouteID(Integer.parseInt(request.getParameter("routeid")));
		trip.setSeats(Integer.parseInt(request.getParameter("seats")));
		customerDao.addNewTrip(trip);
		return "redirect:/updateTrip";
	}

	@RequestMapping("updateAdmin")
	public String updateAdmin(Model adminModel) {
		List<AdminData> adminList = adminService.getAdminData();
		adminModel.addAttribute("adminList", adminList);
		return "admin_modifyAdmin";
	}

	@RequestMapping(value = "addAdmin", method = RequestMethod.GET)
	public String addNewAdmin(HttpServletRequest request) {
		String newAdminName = request.getParameter("newAdminName");
		String newUsername = request.getParameter("newAdminUsername");
		String newPassword = request.getParameter("newAdminPassword");
		customerService.addAdmin(newAdminName, newUsername, newPassword);
		return "admin_newAdminSuccess";
	}

	@RequestMapping(value = "backToAdminHome")
	public String toAdminHome() {
		return "adminHome";
	}

	@RequestMapping("/manageUsers")
	public String manageUsers(Model usersList) {
		List<Customer> userData = customerService.getCustomerData();
		usersList.addAttribute("userData", userData);
		return "admin_userManagement";
	}

	@RequestMapping("customer/{customer.userid}")
	public String deleteUser(HttpServletRequest request) {
		String path = request.getServletPath();
		int userID = Integer.parseInt(path.replaceAll("[\\D]", ""));
		customerService.deleteUser(userID);

		return "redirect:/manageUsers";
	}
	
	
	@RequestMapping("addUserFromAdmin")
	public String addUserFromAdmin(HttpServletRequest request) {

		String username = request.getParameter("username");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("emailID");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		customerService.setUserDetails(username, firstName, lastName, email, address, phone, password);
		return "redirect:/manageUsers";

	}
	@RequestMapping("admin/{adminData.adminID}")
	public String deleteAdmin(HttpServletRequest request) {
		String path = request.getServletPath();
		int adminID = Integer.parseInt(path.replaceAll("[\\D]", ""));
		adminService.deleteAdmin(adminID);

		return "redirect:/updateAdmin";
	}
	

}
