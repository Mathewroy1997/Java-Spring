package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.dao.AdminDao;
import com.project.models.AdminData;
import com.project.models.BusCategory;
import com.project.models.BusDetails;
import com.project.models.CompleteBookingDetails;
import com.project.models.Customer;

import com.project.models.RouteDetails;

import com.project.models.TripDetails;

public class AdminService {

	@Autowired
	AdminDao adminDao;
	
	public List<AdminData> getAdminData() {
		List<AdminData> adminList = adminDao.getAllAdminData();
	
		return adminList;
	}

	public void deleteAdmin(int adminID) {
		adminDao.deleteUser(adminID);
		
	}

	

	public void insertNewAdmin(String newAdminName, String newUsername, String newPassword) {
		adminDao.addNewAdmin(newAdminName, newUsername, newPassword);
		
	}

	public List<Customer> getCustomerData() {
		List<Customer> userData=adminDao.getAllUserData();
		return userData;
	}

	public void deleteCustomer(int userId) {
		adminDao.deleteCustomer(userId);
		
	}

	public void setUserDetails(String username, String firstName, String lastName, String email, String address,
			String phone, String Password) {
		Customer customer = new Customer();

		customer.setUsername(username);
		customer.setFirstname(firstName);
		customer.setLastname(lastName);
		customer.setAddress(address);
		customer.setEmail(email);
		customer.setPhone(phone);
		customer.setPassword(Password);

		adminDao.addNewUserFromAdmin(customer);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public List<BusDetails> getBusDetails() {
		List<BusDetails> busDetailsList=adminDao.getBusDetails();
		return busDetailsList;
	}

	public void addNewBus(BusDetails bus) {
		adminDao.addNewBus(bus);
		
	}

	public List<BusCategory> getBusCategories() {
		List<BusCategory> busCategoryList=adminDao.getBusCategories();
		return busCategoryList;
	}

	public void addNewBusCategory(BusDetails bus) {
		adminDao.addNewBus(bus);
	}

	public void addNewBusCategory(String category) {
		adminDao.addNewBusCategory(category);
		
	}

	public void deleteBus(int busId) {
		adminDao.deleteBus(busId);
		
	}

	public void changeBusDetails(int busId, int ratePerKm, int seats) {
		adminDao.changeBusDetails(busId,ratePerKm,seats);
		
	}

	public List<RouteDetails> getRouteDetails() {
		List<RouteDetails> routeDetailsList=adminDao.getRouteDetails();
		return routeDetailsList;
	}

	public void deleteRouteDetails(int routeId) {
		adminDao.deleteRouteDetails(routeId);
		
	}

	public void addNewRouteDetails(String departure, String destination, int totalDistanceInKm) {
		RouteDetails route=new RouteDetails();
		route.setDeparture(departure);
		route.setDestination(destination);
		route.setTotalDistanceInKm(totalDistanceInKm);
		adminDao.setNewRouteDetails(route);
		
	}

	public List<TripDetails> getTripDetails() {
		List<TripDetails> tripDetailsList=adminDao.getTripDetails();
		return tripDetailsList;
	}

	public void addNewTripDetails(String date, int routeId, int busId, int availableSeats) {
		TripDetails trip=new TripDetails();
		trip.setDate(date);
		trip.setRouteId(routeId);
		trip.setBusId(busId);
		trip.setAvailableSeats(availableSeats);
		
		adminDao.addNewTripDetails(trip);
		
	}

	public void deleteTripDetails(int tripId) {
		adminDao.deleteTripDetails(tripId);
		
		
	}

	public List<CompleteBookingDetails> getBookingHistory() {
		List<CompleteBookingDetails> bookingHistoryList=adminDao.getBookingHistory();
		return bookingHistoryList;
	}
	
	

}
