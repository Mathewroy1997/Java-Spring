package com.project;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.project.*;

@Controller
public class LoginController {
	int rate;
	String registrationStatus;	
	int tickets;
	int totalprice;
	List<Passenger> pass;
@Autowired
CustomerDao dao;
Customer customer;

@Autowired
RouteDao routedao=new RouteDao();
@Autowired
TripDao tripDao=new TripDao();




@RequestMapping(value="/login", method = RequestMethod.GET)

public String login(HttpServletRequest request, Model m) throws DataAccessException, SQLException {
	
	
 String username=request.getParameter("username");
 String password=request.getParameter("password");
List<Customer>list=dao.getData(username,password);
customer=list.get(0);



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
@RequestMapping(value="/bookingpage")
public String DirectToBookingPage(Model m){
	List<Route>list1=routedao.getDeparture();
	m.addAttribute("list", list1);
	
	List<Route>list2=routedao.getDestination();
	m.addAttribute("list2", list2);
	return "newbooking";
}
@RequestMapping(value="/nextPage", method = RequestMethod.GET)
public String nextPage(HttpServletRequest request, Model m) {
	String departure=request.getParameter("database1");
	String destination=request.getParameter("database2");
	
	List<Route> list3=routedao.getRouteData(departure, destination);
	if(list3.isEmpty()) {
		return "unknownRoute";
	}
	Route route4=list3.get(0);
	int route_id=route4.routeID;
	rate=route4.rate;
	//List<Trip> trip1=tripDao.getAvailableSeats(route_id);
	
	m.addAttribute("departure",departure);
	m.addAttribute("destination",destination);
	m.addAttribute("rate",rate);
	m.addAttribute("route_id",route_id); 
	
	String date=request.getParameter("date");
	List<Trip>findTrip=tripDao.CheckDate(date,route_id);
	if(findTrip.isEmpty()) {
		return "bookingpage4";
	}
	else {
		Trip trip=new Trip();
		trip=findTrip.get(0);
		int seatsAvailable=trip.seats;
		m.addAttribute("seats",seatsAvailable);
		return "bookingpage3"; 
	}
	
	
	
//Trip seats=trip1.get(0);
//int seatnumber=seats.seats;

//
	
}

	/*
	 * @RequestMapping(value="/totalPrice", method = RequestMethod.GET) public
	 * String getPassengerInfo(HttpServletRequest request, Model m) { RouteDao dao
	 * =new RouteDao(); int
	 * tickets=Integer.parseInt(request.getParameter("tickets")); int
	 * totalprice=dao.totalPrice(tickets,rate);
	 * m.addAttribute("totalPrice",totalprice); m.addAttribute("tickets",tickets);
	 * return "passengerInfoPage"; }
	 */
@RequestMapping("/getpassenger")

public String getPassengerData(HttpServletRequest request, Model m) {
	String name1=request.getParameter("name");
	int age1=Integer.parseInt(request.getParameter("age"));
	int id1=Integer.parseInt(request.getParameter("id"));
	int userid1=customer.getUserid();
	dao.setPassengerData(name1,age1,id1,userid1);
	
	return "redirect:/call";
	
}
@RequestMapping("/confirmEntries")
public String finalConfirm(Model m) {
	int userid=customer.getUserid();
	List<Passenger> passenger=dao.getPassengerData(userid);
	pass=passenger;
	m.addAttribute("passenger",pass);
	return "finalConfirm";
}
@RequestMapping("/call")
public String call(Model m, HttpServletRequest request) {
	
	m.addAttribute("totalPrice",totalprice);
	m.addAttribute("tickets",tickets);
	List<Temp> temp= dao.callData();
	m.addAttribute("temp",temp);
	return "passengerInfoPage";
}
@RequestMapping("/get")
public String getPrice(HttpServletRequest request, Model m) {
	RouteDao dao1 =new RouteDao();
	 tickets=Integer.parseInt(request.getParameter("tickets"));
	 totalprice=dao1.totalPrice(tickets,rate);
	return "redirect:/call";
}
}





