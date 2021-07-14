package com.project.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
import com.project.service.CustomerService;

@Controller
public class CustomerController {
	
	int rate;
	ViewBookedData viewdata = new ViewBookedData();
	MasterPassengerTable master = new MasterPassengerTable();
	Customer customer;
	List<Passenger> pass;
	List<TempPass> listtemppass;
	int tickets;
	int totalprice;
	
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

	@RequestMapping("/submitNewRegistraion")
	public String newUserRegistration(HttpServletRequest request, HttpServletResponse response) {
		Customer customer = new Customer();
		customer.setUsername(request.getParameter("username"));
		customer.setPassword(request.getParameter("password"));
		customer.setFirstname(request.getParameter("firstName"));
		customer.setLastname(request.getParameter("lastName"));
		customer.setEmail(request.getParameter("emailID"));
		customer.setAddress(request.getParameter("address"));
		customer.setPhone(request.getParameter("phone"));
		customerDao.addNewUser(customer);

		return "newUserAdded";

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
