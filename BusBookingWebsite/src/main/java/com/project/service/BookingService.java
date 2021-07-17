package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.dao.BookingDao;
import com.project.models.AvailableBuses;
import com.project.models.BusDetails;
import com.project.models.Route;
import com.project.models.RouteDetails;
import com.project.models.TemperoryPassengersDetails;
import com.project.models.TripDetails;

public class BookingService {

	@Autowired
	BookingDao bookingDao;

	public List<RouteDetails> getDeparture() {
		List<RouteDetails> departurePoints = bookingDao.getDeparture();
		return departurePoints;
	}

	public List<RouteDetails> getDestination() {
		List<RouteDetails> destinationPoints = bookingDao.getDestination();
		return destinationPoints;
	}

	public List<RouteDetails> getRouteDetails(String departure, String destination) {
		List<RouteDetails> routeDetailsList = bookingDao.getRouteDetails(departure, destination);
		return routeDetailsList;
	}

	public List<TripDetails> checkDateWithRouteId(int routeId, String date) {
		List<TripDetails> tripDetailsList = bookingDao.getTripDetails(routeId, date);
		return tripDetailsList;
	}

	public List<BusDetails> getBusDetails(List<TripDetails> tripListInUserSelectedDate) {
		int numberOfTrips = tripListInUserSelectedDate.size();
		int busId;
		List<BusDetails> getBusDetailsList = new ArrayList<BusDetails>();
		for (int i = 0; i < numberOfTrips; i++) {

			TripDetails tripDetails = tripListInUserSelectedDate.get(i);
			busId = tripDetails.getBusId();
			List<BusDetails> busDetailsList = bookingDao.getBusDetails(busId);
			BusDetails busDetails = busDetailsList.get(0);
			getBusDetailsList.add(busDetails);

		}

		return getBusDetailsList;
	}

	public List<AvailableBuses> getAvailableBuses(List<BusDetails> busDetailsList,
			List<TripDetails> tripListInUserSelectedDate, int totalKm) {

		BusDetails busDetails = new BusDetails();
		TripDetails tripDetails = new TripDetails();
		// AvailableBuses availableBuses=new AvailableBuses();
		List<AvailableBuses> availableBusDetailsList = new ArrayList<AvailableBuses>();

		int totalAvailableBuses = busDetailsList.size();
		for (int i = 0; i < totalAvailableBuses; i++) {
			busDetails = busDetailsList.get(i);
			tripDetails = tripListInUserSelectedDate.get(i);
			int busRatePerKm = busDetails.getBusRatePerKm();
			int ratePerTicket = busRatePerKm * totalKm;
			int availableSeats = tripDetails.getAvailableSeats();
			int tripId = tripDetails.getTripId();
			AvailableBuses availableBuses = new AvailableBuses();
			availableBuses.setBusId(busDetails.getBusId());
			availableBuses.setBusType(busDetails.getBusType());
			availableBuses.setRatePerSeat(ratePerTicket);
			availableBuses.setAvailableSeats(availableSeats);
			availableBuses.setTripId(tripId);
			availableBusDetailsList.add(availableBuses);

		}
		return availableBusDetailsList;
	}

	public int setTotalPrice(int userTickets, int ratePerSeat) {
		int totalPrice = userTickets * ratePerSeat;
		return totalPrice;
	}

	public void setPassengerDetialisToTemperoryTable(int userId, String date, int routeId, int tripId,
			String[] passengerNames, String[] passengerAges, String[] passengerIds) {
		int totalPassengers=passengerAges.length;
		for(int i=0;i<totalPassengers;i++) {
			String passengerName=passengerNames[i];
			String passengerAge=passengerAges[i];
			String passengerId=passengerIds[i];
			bookingDao.setPassengerDetialisToTemperoryTable(userId, date, routeId, tripId, passengerName, passengerAge,
					passengerId);
		}
		

	}

	public List<TemperoryPassengersDetails> setTemperoryPassengerList() {
		List<TemperoryPassengersDetails> temperoryPassengersList=bookingDao.getTemperoryPassengersList();
		return temperoryPassengersList;
	}

	public void reduceSeatsInTripTable(int tripId, int userTickets) {
		bookingDao.reduceSeatsInTripTable(tripId,userTickets);
		
	}

	public void movePassengerTemperoryDetailsToPermanaent() {
		bookingDao.movePassengerTemperoryDetailsToPermanaent();
		
	}

	public void TruncateTemperoryPassengerTable() {
		bookingDao.TruncateTemperoryPassengerTable();	
	}

}
