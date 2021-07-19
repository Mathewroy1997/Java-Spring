package com.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.project.models.CompleteBookingDetails;
import com.project.models.CovidNationalStatus;
import com.project.service.ApiService;

@Controller
public class ApiController {
	@Autowired
	ApiService apiService;

	@RequestMapping(value = "covidStatus")
	public ModelAndView getCovidStatus(Model m, ModelMap map) {
		ModelAndView model = new ModelAndView();
		final String uri = "https://api.apify.com/v2/key-value-stores/toDWvRj1JpTXiM8FF/records/LATEST?disableRedirect=true";

		RestTemplate restTemplate = new RestTemplate();

		CovidNationalStatus api = restTemplate.getForObject(uri, CovidNationalStatus.class);
		map.put("api", api);
		model.setViewName("covidReport");
		return model;
	}

	@RequestMapping(value = "bookingData", method = RequestMethod.GET)
	public ResponseEntity<List<CompleteBookingDetails>> listAllUsers() {
		List<CompleteBookingDetails> users = apiService.getBookingDetails();
		if (users.isEmpty()) {
			return new ResponseEntity<List<CompleteBookingDetails>>(HttpStatus.NO_CONTENT);// You many decide to return
																							// HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<CompleteBookingDetails>>(users, HttpStatus.OK);
	}

}
