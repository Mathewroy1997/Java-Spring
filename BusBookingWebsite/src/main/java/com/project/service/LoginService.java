package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.dao.LoginDao;
import com.project.models.AdminData;
import com.project.models.Customer;

public class LoginService {
	
	@Autowired
	LoginDao loginDao;
	
	
	public int adminLogin(String username, String password) {
		
		List<AdminData> admin = loginDao.getData(username, password);

		int flag;

		if (admin.isEmpty()) {

			return flag = 0;
		} else {

			return flag = 1;

		}
	}
	
	public int loginAsUser(String username, String password) {
		//List<Customer> list = customerDao.getData(username, password);
		List<Customer> list = loginDao.getUserData(username, password);
		
		int flag;

		if (list.isEmpty()) {

			return flag = 0;
		} else {

			return flag = 2;

		}

	}

	public int getUserId(String username, String password) {
		List<Customer> userIDlist = loginDao.getUserData(username,password);
		Customer customer;
		customer = userIDlist.get(0);
		return customer.getUserid();
	}

}
