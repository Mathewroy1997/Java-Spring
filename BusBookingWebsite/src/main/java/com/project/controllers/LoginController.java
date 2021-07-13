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
import com.project.models.TempPass;
import com.project.models.Trip;
import com.project.models.ViewBookedData;
import com.project.service.ServiceClass;
import com.project.*;

@Controller
public class LoginController {
	int rate;
	String registrationStatus;
	int tickets;
	int totalprice;
	List<Passenger> pass;
	List<TempPass> listtemppass;
	ViewBookedData viewdata = new ViewBookedData();
	MasterPassengerTable master = new MasterPassengerTable();
	Route route = new Route();
	Trip trip = new Trip();
	Customer customer;

	@Autowired
	CustomerDao customerDao;

	@Autowired
	RouteDao routeDao;

	@Autowired
	TripDao tripDao;

	@Autowired
	AdminDao adminDao;

	@Autowired
	ServiceClass serviceClass;

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
		} else if (flag == 1) {
			return "adminHome";

		} else {
			customer = userIDlist.get(0);
			viewdata.userid = customer.getUserid();
			listtemppass = null;
			m.addAttribute("username", username);

			return "welcome";
		}
	}

	@RequestMapping("signUp")
	public String registrationPage() {
		return "Register";
	}

	@RequestMapping("logout")
	public String backToIndex() {
		return "index";
	}

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
	public String updateAdmin() {
		return "admin_modifyAdmin";
	}

	@RequestMapping(value = "addAdmin", method = RequestMethod.GET)
	public String addNewAdmin(HttpServletRequest request) {
		String newAdminName = request.getParameter("newAdminName");
		String newUsername = request.getParameter("newAdminUsername");
		String newPassword = request.getParameter("newAdminPassword");
		serviceClass.addAdmin(newAdminName, newUsername, newPassword);
		return "admin_newAdminSuccess";
	}

	@RequestMapping(value = "backToAdminHome")
	public String toAdminHome() {
		return "adminHome";
	}

	@RequestMapping("manageUsers")
	public String manageUsers(Model usersList) {
		List<Customer> userData = serviceClass.getCustomerData();
		usersList.addAttribute("userData", userData);
		return "admin_userManagement";
	}

	@RequestMapping("customer/{customer.userid}")
	public String deleteUser(HttpServletRequest request) {
		String path = request.getServletPath();
		int userID = Integer.parseInt(path.replaceAll("[\\D]", ""));
		serviceClass.deleteUser(userID);

		return "redirect:/manageUsers";
	}

	@RequestMapping("/submitNewRegistraion")
	public String newUserRegistration(HttpServletRequest request, HttpServletResponse response) {
		Customer customer = new Customer();
		customer.setUsername(request.getParameter("username"));
		customer.setPassword(request.getParameter("password"));
		customer.setFirstname(request.getParameter("firstname"));
		customer.setLastname(request.getParameter("lastname"));
		customer.setEmail(request.getParameter("email"));
		customer.setAddress(request.getParameter("address"));
		customer.setPhone(request.getParameter("phone"));
		customerDao.addNewUser(customer);

		return "newUserAdded";

	}

	@RequestMapping("addUserFromAdmin")
	public String addUserFromAdmin(HttpServletRequest request) {

		String username = request.getParameter("username");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		serviceClass.setUserDetails(username, firstName, lastName, email, address, phone, password);
		return "redirect:/manageUsers";

	}

	@RequestMapping(value = "/newBookingPage")
	public String homeToBookingPage(Model m) {
		List<Route> departurePointsList = routeDao.getDeparture();
		m.addAttribute("departurePointsList", departurePointsList);

		List<Route> destinationPointsList = routeDao.getDestination();
		m.addAttribute("destinationPointsList", destinationPointsList);
		return "bookingNew";
	}

	@RequestMapping(value = "/checkRouteAndDateAvailability", method = RequestMethod.POST)
	public String nextPage(HttpServletRequest request, Model m) {
		String departure = request.getParameter("userSelectedDeparture");
		String destination = request.getParameter("userSelectedDestination");

		List<Route> userSelectedRouteInfoList = routeDao.getRouteData(departure, destination);
		if (userSelectedRouteInfoList.isEmpty()) {
			return "unknownRoute";
		}
		viewdata.setDeparture(departure);
		viewdata.setDestination(destination);
		Route userSelectedRouteInfo = userSelectedRouteInfoList.get(0);
		int route_id = userSelectedRouteInfo.routeID;
		rate = userSelectedRouteInfo.rate;
		// List<Trip> trip1=tripDao.getAvailableSeats(route_id);

		m.addAttribute("departure", departure);
		m.addAttribute("destination", destination);
		m.addAttribute("rate", rate);
		m.addAttribute("route_id", route_id);

		String date = request.getParameter("date");
		List<Trip> findTrip = tripDao.CheckDate(date, route_id);
		if (findTrip.isEmpty()) {
			return "returnToNewBooking";
		} else {
			viewdata.setDate(date);
			Trip trip = new Trip();
			trip = findTrip.get(0);
			viewdata.tripid = trip.tripID;
			int seatsAvailable = trip.seats;
			m.addAttribute("seats", seatsAvailable);
			return "bookingSeatSelection";
		}

	}

	@RequestMapping("/getpassenger")

	public String getPassengerData(HttpServletRequest request, Model m) {
		int userid = viewdata.userid;
		String date = viewdata.date;
		int tripid = viewdata.tripid;
		String name1 = request.getParameter("name");
		int age1 = Integer.parseInt(request.getParameter("age"));
		int id1 = Integer.parseInt(request.getParameter("id"));
		master.name = name1;
		master.age = age1;
		master.id = id1;
		int userid1 = customer.getUserid();
		customerDao.saveToTemp(userid, date, tripid, name1, age1, id1);
		listtemppass = customerDao.getFromtemp();

		customerDao.setPassengerData(name1, age1, id1, userid1);

		List<Passenger> passenger = customerDao.getPassengerData(userid1);
		pass = passenger;
		m.addAttribute("passenger", pass);

		return "redirect:/call";

	}

	@RequestMapping("/confirmEntries")
	public String finalConfirm(Model m) {
		master.userid = viewdata.userid;
		master.date = viewdata.date;
		master.tripid = viewdata.tripid;

		customerDao.setViewData(viewdata);
		customerDao.setMasterTable(master);
		customerDao.TruncateTemppass();
		customerDao.ReduceSeats(tickets, master);
		int userid = customer.getUserid();

		return "finalConfirm";
	}

	@RequestMapping("/call")
	public String call(Model m, HttpServletRequest request) {

		m.addAttribute("totalPrice", totalprice);
		m.addAttribute("tickets", tickets);
		List<Temp> temp = customerDao.callData();
		m.addAttribute("temp", temp);
		m.addAttribute("listtemppass", listtemppass);
		return "passengerInfoPage";
	}

	@RequestMapping("/bookingGetPassengerInfo")
	public String getPrice(HttpServletRequest request, Model m) {

		tickets = Integer.parseInt(request.getParameter("tickets"));
		viewdata.setTickets(tickets);
		totalprice = routeDao.totalPrice(tickets, rate);
		return "redirect:/call";
	}

	@RequestMapping("/bookingHistory")
	public String showBookingHistory(Model m) {

		List<ViewBookedData> listBookedHistory = customerDao.setBookedHistory(viewdata.userid);
		m.addAttribute("list", listBookedHistory);
		return "bookingHistory";
	}

}
