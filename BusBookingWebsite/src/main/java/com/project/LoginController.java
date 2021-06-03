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
/*import com.project.Route;
import com.project.RouteDao;
import com.project.CustomerDao;
import com.project.RouteData;*/
@Controller
public class LoginController {
	String registrationStatus;	
	
@Autowired
CustomerDao dao;

/*RouteDao route=new RouteDao();
RouteDao route1=new RouteDao();*/
@Autowired
RouteDao routedao=new RouteDao();


/*
ModelAndView indexPage=new ModelAndView();
ModelAndView profilePage=new ModelAndView();
ModelAndView errorPage=new ModelAndView();
ModelAndView registraionPage=new ModelAndView();

@RequestMapping("/register")
public ModelAndView registerPage() {
	registraionPage.setViewName("Register");
	return registraionPage;
}*/


@RequestMapping(value="/login", method = RequestMethod.GET)

public String login(HttpServletRequest request, Model m) throws DataAccessException, SQLException {
	
	/*errorPage.setViewName("error");
	profilePage.setViewName("welcome");
	indexPage.setViewName("index");*/
 String username=request.getParameter("username");
 String password=request.getParameter("password");
List<Customer>list=dao.getData(username,password);

/*profilePage.addObject("list", list);
request.setAttribute("list",list);*/

if(list.isEmpty()) {
	String error="Invalid Credentials, try again";
	//errorPage.addObject("error", error);
	m.addAttribute("error", error);
return "error";
}
else {
	m.addAttribute("username", username);
	
	List<Route>list1=routedao.getDeparture();
	m.addAttribute("list", list1);
	
	List<Route>list2=routedao.getDestination();
	m.addAttribute("list2", list2);
	
//profilePage.addObject("list",list1);
	//profilePage.addObject("username", username);
	//profilePage.addObject("password",password);
	
		//route.getDeparture();
		
		

	
	
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



}





