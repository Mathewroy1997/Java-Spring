package com.project.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.dao.AdminDao;
import com.project.dao.CustomerDao;
import com.project.models.AdminData;
import com.project.models.Customer;
import com.project.models.Route;

public class ServiceClass {
	HttpServletRequest request;
	@Autowired
	CustomerDao dao;

	@Autowired
	AdminDao adminDao;

	public int login(String username, String password) {
		List<Customer> list = dao.getData(username, password);
		int flag;

		if (list.isEmpty()) {

			return flag = 0;
		} 
		else {

			return flag = 2;

		}

	}

	public void addAdmin(String newAdminName, String newUsername, String newPassword) {

		adminDao.insertNewAdmin(newAdminName, newUsername, newPassword);

	}

	public int adminLogin(String username, String password) {
		List<AdminData> admin = adminDao.getData(username, password);

		int flag;

		if (admin.isEmpty()) {

			return flag = 0;
		} else {

			return flag = 1;

		}
	}
}
