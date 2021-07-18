package com.project.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.project.models.CompleteBookingDetails;

import com.project.service.CustomerService;

@Controller
public class CustomerController {

	
	 
	


	@Autowired
	CustomerService customerService;
	
	
	@RequestMapping(value="userBookingHistory", method=RequestMethod.POST)
	public String userBookingHistory(HttpServletRequest request, Model model) {
		String username=request.getParameter("username");		
		int userId=Integer.parseInt(request.getParameter("userId"));
		List<CompleteBookingDetails> userBookingDetailsList=customerService.getUserBookingHistory(userId);
		
		model.addAttribute("userId",userId);
		model.addAttribute("userBookingDetailsList",userBookingDetailsList);
		model.addAttribute("username",username);
		return "userBookingHistory";
	}
	
	
@RequestMapping("/submitNewRegistraion")
	public String newUserRegistration(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String emailId=request.getParameter("emailId");
		String address=request.getParameter("address");
		String phone=request.getParameter("phone");
		
		try {
			customerService.addNewUser(username,password,firstName,lastName,emailId,address,phone);
			
			return "newUserAdded";
		} catch (Exception e) {

			String duplicateError = "Username already exist.Try again";
			model.addAttribute("duplicateMessage", duplicateError);
			return "Register";
		}

	}
	
}
