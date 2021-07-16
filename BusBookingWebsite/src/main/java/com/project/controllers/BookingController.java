package com.project.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.models.Route;
import com.project.models.RouteDetails;
import com.project.models.Trip;
import com.project.models.TripDetails;
import com.project.service.BookingService;



@Controller
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
	@RequestMapping(value = "newBookingPage", method=RequestMethod.POST)
	public String homeToBookingPage(HttpServletRequest request, Model m) {
		
		int userId=Integer.parseInt(request.getParameter("userId"));

		List<RouteDetails> departurePointsList = bookingService.getDeparture();
		m.addAttribute("departurePointsList", departurePointsList);

		List<RouteDetails> destinationPointsList = bookingService.getDestination();
		m.addAttribute("destinationPointsList", destinationPointsList);
		m.addAttribute("userId", userId);
		return "bookingNew";
	}
	
	/**
	 * @param request
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "/checkRouteAndDateAvailability", method = RequestMethod.POST)
	public String nextPage( HttpServletRequest request, Model m) {
		int userId=Integer.parseInt(request.getParameter("userId"));
		String departure = request.getParameter("userSelectedDeparture");
		String destination = request.getParameter("userSelectedDestination");
		String date = request.getParameter("date");

		List<RouteDetails> userSelectedRouteDetailsList = bookingService.getRouteDetails(departure, destination);
		if (userSelectedRouteDetailsList.isEmpty()) {
			return "unknownRoute";
		}
		RouteDetails routeDetails=userSelectedRouteDetailsList.get(0);
		int routeId=routeDetails.getRouteId();
		List<TripDetails> tripListInUserSelectedDate=bookingService.checkDateWithRouteId(routeId,date);
		int numberOfTrips=tripListInUserSelectedDate.size();
		List<TripDetails> tripListToGetBusDetails=new ArrayList<TripDetails>();
		for(int i=0;i<numberOfTrips;i++) {
			
			TripDetails tripDetails=tripListInUserSelectedDate.get(i);
			tripListToGetBusDetails.add(tripDetails);

			int j=0;
			int g=j;
		}
		List<TripDetails> list=tripListToGetBusDetails;
		int f;

		return "bookingSeatSelection";

		/*RouteDetails userSelectedRouteInfo = userSelectedRouteDetailsList.get(0);
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
			return "bookingSeatSelection";*/
		

	}


}
