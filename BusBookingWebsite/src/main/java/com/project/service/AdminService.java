package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.dao.AdminDao;
import com.project.models.AdminData;
import com.project.models.Customer;

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
	
	

}
