package com.project.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.project.models.TemperoryPassenger;
import com.project.models.Trip;
import com.project.models.ViewBookedData;
import com.project.service.CustomerService;

@Controller
public class CustomerController {

	 int userTickets;
	 
	@Autowired
	CustomerDao customerDao;
	@Autowired
	RouteDao routeDao;

	@Autowired
	TripDao tripDao;

	@Autowired
	CustomerService serviceClass;

	@RequestMapping("/submitNewRegistraion")
	public String newUserRegistration(HttpServletRequest request, HttpServletResponse response, Model model) {
		Customer customer = new Customer();
		customer.setUsername(request.getParameter("username"));
		customer.setPassword(request.getParameter("password"));
		customer.setFirstname(request.getParameter("firstName"));
		customer.setLastname(request.getParameter("lastName"));
		customer.setEmail(request.getParameter("emailId"));
		customer.setAddress(request.getParameter("address"));
		customer.setPhone(request.getParameter("phone"));
		try {
			customerDao.addNewUser(customer);
			return "newUserAdded";
		} catch (Exception e) {

			String duplicateError = "Username already exist.Try again";
			model.addAttribute("duplicateMessage", duplicateError);
			return "Register";
		}

	}

	@RequestMapping(value = "newBookingPage1", method=RequestMethod.POST)
	public String homeToBookingPage(HttpServletRequest request, Model m) {
		
		int userId=Integer.parseInt(request.getParameter("userId"));

		List<Route> departurePointsList = routeDao.getDeparture();
		m.addAttribute("departurePointsList", departurePointsList);

		List<Route> destinationPointsList = routeDao.getDestination();
		m.addAttribute("destinationPointsList", destinationPointsList);
		m.addAttribute("userId", userId);
		return "bookingNew";
	}

	@RequestMapping(value = "/checkRouteAndDateAvailability1", method = RequestMethod.POST)
	public String nextPage( HttpServletRequest request, Model m) {
		int userId=Integer.parseInt(request.getParameter("userId"));
		String departure = request.getParameter("userSelectedDeparture");
		String destination = request.getParameter("userSelectedDestination");

		List<Route> userSelectedRouteInfoList = routeDao.getRouteData(departure, destination);
		if (userSelectedRouteInfoList.isEmpty()) {
			return "unknownRoute";
		}

		Route userSelectedRouteInfo = userSelectedRouteInfoList.get(0);
		int routeId = userSelectedRouteInfo.getRouteID();
		int ratePerTicket = userSelectedRouteInfo.getRate();

		m.addAttribute("departure", departure);
		m.addAttribute("destination", destination);
		m.addAttribute("rate", ratePerTicket);
		m.addAttribute("routeId", routeId);
		m.addAttribute("userId", userId);

		String date = request.getParameter("date");
		List<Trip> findTrip = tripDao.CheckDate(date, routeId);
		if (findTrip.isEmpty()) {
			return "returnToNewBooking";
		} else {

			Trip trip = new Trip();
			trip = findTrip.get(0);

			int seatsAvailable = trip.getSeats();
			m.addAttribute("date", date);
			m.addAttribute("seats", seatsAvailable);
			m.addAttribute("tripId", trip.getTripID());
			return "bookingSeatSelection";
		}

	}

	@RequestMapping(value = "/bookingGetPassengerInfo", method=RequestMethod.GET)
	public String getPrice( HttpServletRequest request, Model model) {
		
		int userId=Integer.parseInt(request.getParameter("userId"));
		int routeId=Integer.parseInt(request.getParameter("routeId"));
		String date=request.getParameter("date");
		int rate=Integer.parseInt(request.getParameter("rate"));
		int tripId=Integer.parseInt(request.getParameter("tripId"));
		
		userTickets = Integer.parseInt(request.getParameter("userEntryTickets"));

		int totalPrice = routeDao.totalPrice(userTickets, rate);
		model.addAttribute("userTickets",userTickets);
		model.addAttribute("totalPrice", totalPrice);
		
		model.addAttribute("userId",userId);
		model.addAttribute("date",date);
		model.addAttribute("rate",rate);
		model.addAttribute("tripId",tripId);
		model.addAttribute("routeId",routeId);
		

		return "redirect:/reloadGetPassenger";
	}
	

	@RequestMapping(value="/reloadGetPassenger", method=RequestMethod.GET)
	public String call( Model model, HttpServletRequest request) {
		
		
		int i=0;
		int j=i;
		int userId=Integer.parseInt(request.getParameter("userId"));
		int routeId=Integer.parseInt(request.getParameter("routeId"));
		String date=request.getParameter("date");
		int rate=Integer.parseInt(request.getParameter("rate"));
		int tripId=Integer.parseInt(request.getParameter("tripId"));
		int totalPrice=Integer.parseInt(request.getParameter("totalPrice"));
		

		model.addAttribute("userId", userId);
		model.addAttribute("routeId", routeId);
		model.addAttribute("date", date);
		model.addAttribute("tripId", tripId);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("rate", rate);
		

		List<TemperoryPassenger> listTemporaryPassengers = customerDao.getFromTemporaryPassenger();
		model.addAttribute("listTemporaryPassengers", listTemporaryPassengers);
		return "passengerInfoPage";
	}

	@RequestMapping(value = "/getpassenger", method=RequestMethod.GET)
	public String getPassengerData( HttpServletRequest request, Model model) {
		
		String name1 = request.getParameter("name");
		int age1 = Integer.parseInt(request.getParameter("age"));
		int id1 = Integer.parseInt(request.getParameter("id"));
		
		int userId=Integer.parseInt(request.getParameter("userId"));
		int routeId=Integer.parseInt(request.getParameter("routeId"));
		String date=request.getParameter("date");
		int rate=Integer.parseInt(request.getParameter("rate"));
		int tripId=Integer.parseInt(request.getParameter("tripId"));
		int totalPrice=Integer.parseInt(request.getParameter("totalPrice"));
		

		customerDao.saveToTemporaryPassengers(userId, date, tripId, name1, age1, id1);

		
		  model.addAttribute("userId", userId); 
		  model.addAttribute("routeId", routeId);
		  model.addAttribute("date", date); 
		  model.addAttribute("tripId", tripId);
		  model.addAttribute("totalPrice", totalPrice);
		  model.addAttribute("rate",rate);
		  
		 
		return "redirect:/reloadGetPassenger";
	

	}

	@RequestMapping(value = "/confirmEntries", method = RequestMethod.POST)
	public String finalConfirm(HttpServletRequest request, Model model) {
		
	
		int userId=Integer.parseInt(request.getParameter("userId"));
		int routeId=Integer.parseInt(request.getParameter("routeId"));
		String date=request.getParameter("date");
		int rate=Integer.parseInt(request.getParameter("rate"));
		int tripId=Integer.parseInt(request.getParameter("tripId"));
		int totalPrice=Integer.parseInt(request.getParameter("totalPrice"));
		
		
		model.addAttribute("userId", userId);
		model.addAttribute("routeId", routeId);
		model.addAttribute("date", date);
		model.addAttribute("tripId", tripId);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("rate", rate);
		
		customerDao.moveDataFromTemporaryToMaster();
		customerDao.TruncateTemporaryTable();
		customerDao.ReduceSeats(userTickets, tripId, date);
		int j;

		return "finalConfirm";
	}

	@RequestMapping("/bookingHistory")
	public String showBookingHistory(Model m) {

		return "bookingHistory";
	}

}
