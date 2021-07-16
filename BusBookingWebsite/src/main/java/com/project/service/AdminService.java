package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.dao.AdminDao;
import com.project.models.AdminData;
import com.project.models.Customer;
import com.project.models.Route;
import com.project.models.Trip;

public class AdminService {

	@Autowired
	AdminDao adminDao;
	
	public List<AdminData> getAdminData() {
		List<AdminData> adminList = adminDao.getAllAdminData();
		int i;
		return adminList;
	}

	public void deleteAdmin(int adminID) {
		adminDao.deleteUser(adminID);
		
	}

	public List<Route> getRouteTable() {
		List<Route> routeTable=adminDao.getRouteTable();
		return routeTable;
	}

	public void addNewRoute(Route route) {
		adminDao.addNewRoute(route);
		
	}

	public void deleteRoute(int routeId) {
		adminDao.deleteRoute(routeId);
		
	}

	public List<Trip> getTripData() {
		List<Trip> tripData=adminDao.getTripData();
		return tripData;
	}

	public void addNewTrip(Trip trip) {
		adminDao.addNewTrip(trip);
		
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
	
	

}
