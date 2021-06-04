package com.project;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.project.*;

@Controller
public class LoginController {
	String registrationStatus;	
	
@Autowired
CustomerDao dao;


@Autowired
RouteDao routedao=new RouteDao();




@RequestMapping(value="/login", method = RequestMethod.GET)

public String login(HttpServletRequest request, Model m) throws DataAccessException, SQLException {
	
	
 String username=request.getParameter("username");
 String password=request.getParameter("password");
List<Customer>list=dao.getData(username,password);



if(list.isEmpty()) {
	String error="Invalid Credentials, try again";
	
	m.addAttribute("error", error);
return "error";
}
else {
	m.addAttribute("username", username);
	
	List<Route>list1=routedao.getDeparture();
	m.addAttribute("list", list1);
	
	List<Route>list2=routedao.getDestination();
	m.addAttribute("list2", list2);
	


return "welcome";

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
@RequestMapping(value="/nextPage", method = RequestMethod.GET)
public String nextPage(HttpServletRequest request) {
	String departure=request.getParameter("database1");
	String destination=request.getParameter("database2");
	List<Route> list3=routedao.getRouteData(departure, destination);
	return "bookingpage2";
}
}







