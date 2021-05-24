package com.project;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.Customer;
import com.project.CustomerDao;
import com.project.Route;
import com.project.RouteDao;

@Controller
public class LoginController {
	String registrationStatus;	
	
@Autowired
CustomerDao dao;

RouteDao route=new RouteDao();
RouteDao route1=new RouteDao();
@Autowired
RouteDao routedao=new RouteDao();



ModelAndView indexPage=new ModelAndView();
ModelAndView profilePage=new ModelAndView();
ModelAndView errorPage=new ModelAndView();
ModelAndView registraionPage=new ModelAndView();

@RequestMapping("/register")
public ModelAndView registerPage() {
	registraionPage.setViewName("Register");
	return registraionPage;
}


@RequestMapping("/login")

public ModelAndView login(HttpServletRequest request) throws DataAccessException, SQLException {
	errorPage.setViewName("error");
	profilePage.setViewName("welcome");
	indexPage.setViewName("index");
 String username=request.getParameter("username");
 String password=request.getParameter("password");
List<Customer>list=dao.getData(username,password);
if(list.isEmpty()) {
	String error="Invalid Credentials, try again";
	errorPage.addObject("error", error);
	
return errorPage;
}
else {
	String d1 = null;
	String d2 = null;
	String d3 = null;
	String d4 = null;
	List<Route>list1=routedao.getDeparture(d1, d2, d3, d4);
	profilePage.addObject("username", username);
	profilePage.addObject("d1",d1);
	profilePage.addObject("d2",d2);
	profilePage.addObject("d3",d3);
	profilePage.addObject("d4",d4);
	
		//route.getDeparture();
		

	
	
return profilePage;

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





