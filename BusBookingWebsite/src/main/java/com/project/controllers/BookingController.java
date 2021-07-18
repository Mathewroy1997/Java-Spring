package com.project.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.models.AvailableBuses;
import com.project.models.BusDetails;
import com.project.models.Route;
import com.project.models.RouteDetails;
import com.project.models.TemperoryPassengersDetails;
import com.project.models.Trip;
import com.project.models.TripDetails;
import com.project.service.BookingService;

@Controller
public class BookingController {

	@Autowired
	BookingService bookingService;

	@RequestMapping(value = "newBookingPage", method = RequestMethod.POST)
	public String homeToBookingPage(HttpServletRequest request, Model model) {
		bookingService.TruncateTemperoryPassengerTable();

		int userId = Integer.parseInt(request.getParameter("userId"));

		List<RouteDetails> departurePointsList = bookingService.getDeparture();
		model.addAttribute("departurePointsList", departurePointsList);

		List<RouteDetails> destinationPointsList = bookingService.getDestination();
		String userName=request.getParameter("username");
		model.addAttribute("username",userName);
		model.addAttribute("destinationPointsList", destinationPointsList);
		model.addAttribute("userId", userId);
		return "userBookingNew";
	}

	
	@RequestMapping(value = "/checkRouteAndDateAvailability", method = RequestMethod.POST)
	public String checkRouteAndDate(HttpServletRequest request, Model model) {
		int userId = Integer.parseInt(request.getParameter("userId"));
		String username=request.getParameter("username");
		String departure = request.getParameter("userSelectedDeparture");
		String destination = request.getParameter("userSelectedDestination");
		String date = request.getParameter("date");

		List<RouteDetails> userSelectedRouteDetailsList = bookingService.getRouteDetails(departure, destination);
		if (userSelectedRouteDetailsList.isEmpty()) {
			
			model.addAttribute("userId", userId);
			model.addAttribute("username",username);
			return "userBookingUnknownRoute";
		}
		RouteDetails routeDetails = userSelectedRouteDetailsList.get(0);
		int routeId = routeDetails.getRouteId();
		int totalKm = routeDetails.getTotalDistanceInKm();
		
		List<TripDetails> tripListInUserSelectedDate = bookingService.checkDateWithRouteId(routeId, date);
		if(tripListInUserSelectedDate.isEmpty()) {
			model.addAttribute("userId", userId);
			model.addAttribute("username",username);
			return "userBookingUnknownRoute";
		}

		List<BusDetails> busDetailsList = bookingService.getBusDetails(tripListInUserSelectedDate);
		List<AvailableBuses> availableBusList = bookingService.getAvailableBuses(busDetailsList,
				tripListInUserSelectedDate, totalKm);
		
		model.addAttribute("username",username);

		model.addAttribute("availableBusList", availableBusList);
		model.addAttribute("userId", userId);
		model.addAttribute("date", date);
		model.addAttribute("departure", departure);
		model.addAttribute("destination", destination);
		model.addAttribute("totalKm", totalKm);
		model.addAttribute("routeId",routeId);

		return "userBookingShowAvailableBuses";

	}
	
	@RequestMapping(value="passUserEntries", method = RequestMethod.POST)
	public String verifyUserEntries(HttpServletRequest request,Model model) {
		
		int userId=Integer.parseInt(request.getParameter("userId")) ;
		String date=request.getParameter("date");
		String departure=request.getParameter("departure");
		String destination=request.getParameter("destination");
		int totalKm=Integer.parseInt(request.getParameter("totalKm"));
		int busId=Integer.parseInt(request.getParameter("busId"));
		String busType=request.getParameter("busType");
		int availableSeats=Integer.parseInt(request.getParameter("availableSeats"));
		int userTickets=Integer.parseInt(request.getParameter("userTickets"));
		int ratePerSeat=Integer.parseInt(request.getParameter("ratePerSeat"));
		int tripId=Integer.parseInt(request.getParameter("tripId"));
		int routeId=Integer.parseInt(request.getParameter("routeId"));
		
		int totalPrice=bookingService.setTotalPrice(userTickets,ratePerSeat);
		String username=request.getParameter("username");
		model.addAttribute("username",username);
		
		model.addAttribute("userId", userId);
		model.addAttribute("date", date);
		model.addAttribute("departure", departure);
		model.addAttribute("destination", destination);
		model.addAttribute("totalKm", totalKm);
		model.addAttribute("routeId",routeId);
		model.addAttribute("totalPrice",totalPrice);
		model.addAttribute("busId",busId);
		model.addAttribute("busType",busType);
		model.addAttribute("tripId",tripId);
		model.addAttribute("routeId",routeId);
		model.addAttribute("userTickets",userTickets);
		
		
		return "userBookingVerifyUserEntries";
	}
	@RequestMapping(value="goToAddPassengerDetials", method=RequestMethod.POST)
	public String goToAddPassengerDetails(HttpServletRequest request, Model model) {
		
		
		int userTickets=Integer.parseInt(request.getParameter("userTickets"));
		int userId=Integer.parseInt(request.getParameter("userId"));
		String date=request.getParameter("date");
		int routeId=Integer.parseInt(request.getParameter("routeId"));
		int tripId=Integer.parseInt(request.getParameter("tripId"));
		String departure=request.getParameter("departure");
		String destination=request.getParameter("destination");
		int busId=Integer.parseInt(request.getParameter("busId"));
		String busType=request.getParameter("busType");
		int totalPrice=Integer.parseInt(request.getParameter("totalPrice"));
		String username=request.getParameter("username");
		model.addAttribute("username",username);
		
		model.addAttribute("userTickets",userTickets);
		model.addAttribute("userId", userId);
		model.addAttribute("date", date);
		model.addAttribute("routeId",routeId);
		model.addAttribute("tripId",tripId);
		model.addAttribute("departure", departure);
		model.addAttribute("destination", destination);
		model.addAttribute("busId",busId);
		model.addAttribute("busType",busType);
		model.addAttribute("totalPrice",totalPrice);
		
		return "userBookingAddPassengers";
	}
	@RequestMapping(value="addPassengersToTemperoryTable", method=RequestMethod.POST)
	public String proceedToPayment(HttpServletRequest request, Model model) {
		String[] passengerNames = request.getParameterValues("passengerName");
		String[] passengerAges = request.getParameterValues("passengerAge");
		String[] passengerIds = request.getParameterValues("passengerId");
		
		int userId=Integer.parseInt(request.getParameter("userId"));
		String date=request.getParameter("date");
		int routeId=Integer.parseInt(request.getParameter("routeId"));
		int tripId=Integer.parseInt(request.getParameter("tripId"));
		String departure=request.getParameter("departure");
		String destination=request.getParameter("destination");
		int busId=Integer.parseInt(request.getParameter("busId"));
		String busType=request.getParameter("busType");
		int totalPrice=Integer.parseInt(request.getParameter("totalPrice"));
		int userTickets=Integer.parseInt(request.getParameter("userTickets"));
		
		String username=request.getParameter("username");
		model.addAttribute("username",username);
		
		bookingService.setPassengerDetialisToTemperoryTable(userId,date,routeId,departure,destination,tripId,busId,busType,passengerNames,passengerAges,passengerIds);
		List<TemperoryPassengersDetails> temperoryPassengerList=bookingService.setTemperoryPassengerList(); 
		model.addAttribute("userId", userId);
		model.addAttribute("date", date);
		model.addAttribute("departure", departure);
		model.addAttribute("destination", destination);
		model.addAttribute("busId",busId);
		model.addAttribute("busType",busType);
		model.addAttribute("temperoryPassengerList",temperoryPassengerList);
		model.addAttribute("totalPrice",totalPrice);
		model.addAttribute("userTickets",userTickets);
		model.addAttribute("tripId",tripId);
		
		return "userBookingFinalConfirmation";
	}
	@RequestMapping(value="toPayment")
	public String paymentSection(HttpServletRequest request,Model model) {
		int userId=Integer.parseInt(request.getParameter("userId"));
		int userTickets=Integer.parseInt(request.getParameter("userTickets"));
		int tripId=Integer.parseInt(request.getParameter("tripId"));
		String username=request.getParameter("username");
		model.addAttribute("username",username);
		model.addAttribute("userId", userId);
		bookingService.reduceSeatsInTripTable(tripId,userTickets);
		bookingService.movePassengerTemperoryDetailsToPermanaent();
		bookingService.TruncateTemperoryPassengerTable();
		return "userBookingPaymentGateway";
	}

}
