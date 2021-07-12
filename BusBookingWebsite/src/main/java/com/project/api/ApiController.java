package com.project.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApiController {
	@RequestMapping(value="/hello")
	public ModelAndView getCovidStatus(Model m, ModelMap map) {
		ModelAndView model= new ModelAndView();
		final String uri = "https://api.apify.com/v2/key-value-stores/toDWvRj1JpTXiM8FF/records/LATEST?disableRedirect=true";
		 
	    //TODO: Autowire the RestTemplate in all the examples
	    RestTemplate restTemplate = new RestTemplate();
	  
	    CovidNationalStatus api = restTemplate.getForObject(uri, CovidNationalStatus .class);
	    map.put("api",api);
	    model.setViewName("covidReport");
	    return model;
	}
		

}
