package com.project;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.Customer;
import com.project.CustomerDao;

@Controller
public class LoginController {
	String registrationStatus;	
	
@Autowired
CustomerDao dao;




ModelAndView welcomePage=new ModelAndView();
ModelAndView errorPage=new ModelAndView();
ModelAndView registraionPage=new ModelAndView();

@RequestMapping("/register")
public ModelAndView registerPage() {
	registraionPage.setViewName("Register");
	return registraionPage;
}


@RequestMapping("/login")

public ModelAndView login(HttpServletRequest request) {
	welcomePage.setViewName("welcome");
	errorPage.setViewName("error");
 String username=request.getParameter("username");
 String password=request.getParameter("password");
List<Customer>list=dao.getData(username,password);
if(list.isEmpty()) {
	
return errorPage;
}
else {
return welcomePage;

}	
}

@RequestMapping("/submitNewRegistraion")
public String registerprocess(HttpServletRequest request,HttpServletResponse response) {
Customer customer =new Customer();
customer.setUsername(request.getParameter("username"));
customer.setPassword(request.getParameter("password"));
customer.setFirstname(request.getParameter("firstname"));
customer.setLastname(request.getParameter("lastname"));
customer.setEmail(request.getParameter("email"));
customer.setAddress(request.getParameter("address"));
customer.setPhone(request.getParameter("phone"));
dao.saveEmployeeByPreparedStatement(customer);

return "Success";

}
}

