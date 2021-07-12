package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.dao.CustomerDao;
import com.project.models.Customer;
import com.project.models.Route;

public class ServiceClass {
	
	@Autowired
	CustomerDao dao;
	
	public int login(String username,String  password) {
		List<Customer>list=dao.getData(username,password);
		int flag;


		if(list.isEmpty()) {
			
			
			

		return flag=0;
		}
		else if(username.contentEquals("admin") && password.contentEquals("1234")) {
			return flag=1;
			
		}
		else {
//			customer=list.get(0);
//			viewdata.userid=customer.getUserid();
//			listtemppass=null;
//			m.addAttribute("username", username);
//			
//			List<Route>list1=routedao.getDeparture();
//			m.addAttribute("list", list1);
//			
//			List<Route>list2=routedao.getDestination();
//			m.addAttribute("list2", list2);
//			


			return flag=2;
		
	
	}
	
}}
