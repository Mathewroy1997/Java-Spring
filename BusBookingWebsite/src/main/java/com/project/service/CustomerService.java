package com.project.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;


import com.project.dao.CustomerDao;

import com.project.models.CompleteBookingDetails;
import com.project.models.Customer;

public class CustomerService {

	@Autowired
	CustomerDao customerDao;

	public List<Customer> getCustomerData() {
		List<Customer> userData = customerDao.getAllUserData();

		return userData;
	}

	public void deleteUser(int userId) {
		customerDao.deleteCustomer(userId);

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

		customerDao.addNewUserFromAdmin(customer);
	}

	public List<CompleteBookingDetails> getUserBookingHistory(int userId) {
		List<CompleteBookingDetails> userBookingDetailsList = customerDao.getUserBookingHistory(userId);
		return userBookingDetailsList;
	}

	public void addNewUser(String username, String password, String firstName, String lastName, String emailId,
			String address, String phone) {
		Customer customer = new Customer();
		customer.setUsername("username");
		customer.setPassword(("password"));
		customer.setFirstname(("firstName"));
		customer.setLastname(("lastName"));
		customer.setEmail(("emailId"));
		customer.setAddress(("address"));
		customer.setPhone(("phone"));

		customerDao.addNewUser(customer);

	}
}
