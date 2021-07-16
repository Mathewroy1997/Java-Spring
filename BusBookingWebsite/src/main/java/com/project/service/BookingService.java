package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.dao.BookingDao;
import com.project.models.Route;
import com.project.models.RouteDetails;
import com.project.models.TripDetails;

public class BookingService {
	
@Autowired
BookingDao bookingDao;

	public List<RouteDetails> getDeparture() {
		List<RouteDetails> departurePoints=bookingDao.getDeparture();
		return departurePoints;
	}
	public List<RouteDetails> getDestination() {
		List<RouteDetails> destinationPoints=bookingDao.getDestination();
		return destinationPoints;
	}
	public List<RouteDetails> getRouteDetails(String departure, String destination) {
		List<RouteDetails> routeDetailsList=bookingDao.getRouteDetails(departure, destination);
		return routeDetailsList;
	}
	public List<TripDetails> checkDateWithRouteId(int routeId,String date) {
		List<TripDetails> tripDetailsList=bookingDao.getTripDetails(routeId,date);
		return tripDetailsList;
	}
	

}
