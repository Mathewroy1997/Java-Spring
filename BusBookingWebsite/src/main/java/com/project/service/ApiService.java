package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.dao.ApiDao;
import com.project.models.CompleteBookingDetails;

public class ApiService {
	@Autowired
	ApiDao apiDao;

	public List<CompleteBookingDetails> getBookingDetails() {
		List<CompleteBookingDetails> bookingDetails=apiDao.getBookingDetails();
		return bookingDetails;
	}

}
