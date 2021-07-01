package com.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerRestApi {
	@Autowired
	CustomerDao dao;
	
	@RequestMapping(value = "bookingData", method = RequestMethod.GET)
    public ResponseEntity<List<MasterPassengerTable>> listAllUsers() {
        List<MasterPassengerTable> users =dao.getFromMasterPassenger();
        if(users.isEmpty()){
            return new ResponseEntity<List<MasterPassengerTable>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<MasterPassengerTable>>(users, HttpStatus.OK);
    }
	
	
	

}