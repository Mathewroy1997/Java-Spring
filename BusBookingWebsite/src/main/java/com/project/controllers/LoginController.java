package com.project.controllers;

import java.net.http.HttpRequest;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.project.*;
import com.project.dao.CustomerDao;
import com.project.dao.RouteDao;
import com.project.dao.TripDao;
import com.project.models.Customer;
import com.project.models.MasterPassengerTable;
import com.project.models.Passenger;
import com.project.models.Route;
import com.project.models.Temp;
import com.project.models.TempPass;
import com.project.models.Trip;
import com.project.models.ViewBookedData;
import com.project.service.ServiceClass;

@Controller
public class LoginController {
	int rate;
	String registrationStatus;	
	int tickets;
	int totalprice;
	List<Passenger> pass;
	List<TempPass> listtemppass;
	ViewBookedData viewdata=new ViewBookedData();
	MasterPassengerTable master=new MasterPassengerTable();
	Route route= new Route();
	Trip trip = new Trip();
	@Autowired
CustomerDao dao;
Customer customer;

@Autowired
RouteDao routedao=new RouteDao();
@Autowired
TripDao tripDao=new TripDao();
@Autowired
ServiceClass serviceClass;




@RequestMapping(value="/login", method = RequestMethod.GET)

public String login(HttpServletRequest request, Model m)  {
	
	
String username=request.getParameter("username");
 
String password=request.getParameter("password");

int flag=serviceClass.login(username, password);

List<Customer>list=dao.getData(username,password);



if(flag==0) {
	String error="Invalid Credentials";
	
	m.addAttribute("error", error);

return "error";
}
else if(flag==1) {
	return "adminHome";
	
}
else  {
	customer=list.get(0);
	viewdata.userid=customer.getUserid();
	listtemppass=null;
	m.addAttribute("username", username);
	
	List<Route>list1=routedao.getDeparture();
	m.addAttribute("list", list1);
	
	List<Route>list2=routedao.getDestination();
	m.addAttribute("list2", list2);
	


return "welcome";
}
}

@RequestMapping("signUp")
public String registrationPage() {
return "Register";
}
@RequestMapping("logout")
public String backToIndex() {
	return "index";
}

@RequestMapping("/updateRoute")
public String updateRoute(Model m) {
	List<Route> routeTable=routedao.getRouteTable();
	route=routeTable.get(0);
	m.addAttribute("routeTable",routeTable);
	
	return "modifyRoute";
}
@RequestMapping("/addRoute")
public String addNewRoute(HttpServletRequest request, Model m) {
	Route newRoute=new Route();
	newRoute.setRouteID(Integer.parseInt(request.getParameter("routeid")));
	newRoute.setDeparture(request.getParameter("departure"));
	newRoute.setDestination(request.getParameter("destination"));
	newRoute.setRate(Integer.parseInt(request.getParameter("rate")));
	
	routedao.addNewRoute(newRoute);
	return "redirect:/updateRoute";	
	
}
@RequestMapping("route/{route3.routeID}")
public String deleteRoute(HttpServletRequest request) {
	
	
	//int id=Integer.parseInt(request.getParameter("route"));
	String path = request.getServletPath();
	
	int routeid=Integer.parseInt(path.replaceAll("[\\D]", ""));
	Model m;
	
	try {
		dao.deleteRoute(routeid);
		return "redirect:/updateRoute";
	} catch (DataIntegrityViolationException e) {
		return "admin_routeidexception";
	}
	
}
@RequestMapping("/backToRouteIDUpdation")
public String returnToRouteIDUpdation()
{
	return "redirect:/updateRoute";
}

@RequestMapping("updateTrip")
public String updateTrip(Model m) {
	
	List<Trip> tripTable=dao.getTripData();
	trip=tripTable.get(0);
	m.addAttribute("tripTable",tripTable);
	return "admin_modifyTrip";
}
@RequestMapping("trip/{trip.tripID}")
public String deleteTrip(HttpServletRequest request) {
	String path = request.getServletPath();
	int tripid=Integer.parseInt(path.replaceAll("[\\D]", ""));
	dao.deleteTrip(tripid);
	return "redirect:/updateTrip";
}

@RequestMapping("addTrip")
public String addNewTrip(HttpServletRequest request, Model m) {
	Trip trip =new Trip();
	trip.setTripID(Integer.parseInt(request.getParameter("tripid")));
	trip.setDate(request.getParameter("date"));
	trip.setRouteID(Integer.parseInt(request.getParameter("routeid")));
	trip.setSeats(Integer.parseInt(request.getParameter("seats")));
	dao.addNewTrip(trip);
	return "redirect:/updateTrip";
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
	viewdata.setDeparture(departure);
	viewdata.setDestination(destination);
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
		viewdata.setDate(date);
		Trip trip=new Trip();
		trip=findTrip.get(0);
		viewdata.tripid=trip.tripID;
		int seatsAvailable=trip.seats;
		m.addAttribute("seats",seatsAvailable);
		return "bookingpage3"; 
	}
	
	
	

	
}


@RequestMapping("/getpassenger")

public String getPassengerData(HttpServletRequest request, Model m) {
	int userid=viewdata.userid;
	String date=viewdata.date;
	int tripid=viewdata.tripid;
	String name1=request.getParameter("name");
	int age1=Integer.parseInt(request.getParameter("age"));
	int id1=Integer.parseInt(request.getParameter("id"));
	master.name=name1;
	master.age=age1;
	master.id=id1;
	int userid1=customer.getUserid();
dao.saveToTemp(userid,date,tripid,name1,age1,id1);
listtemppass=dao.getFromtemp();

	dao.setPassengerData(name1,age1,id1,userid1);
	
	List<Passenger> passenger=dao.getPassengerData(userid1);
	pass=passenger;
	m.addAttribute("passenger",pass);
	
	return "redirect:/call";
	
}
@RequestMapping("/confirmEntries")
public String finalConfirm(Model m) {
	master.userid=viewdata.userid;
	master.date=viewdata.date;
	master.tripid=viewdata.tripid;
	
	dao.setViewData(viewdata);
	dao.setMasterTable( master);
	dao.TruncateTemppass();
	dao.ReduceSeats(tickets, master);
	int userid=customer.getUserid();
		
	return "finalConfirm";
}
@RequestMapping("/call")
public String call(Model m, HttpServletRequest request) {
	
	m.addAttribute("totalPrice",totalprice);
	m.addAttribute("tickets",tickets);
	List<Temp> temp= dao.callData();
	m.addAttribute("temp",temp);
	m.addAttribute("listtemppass",listtemppass);
	return "passengerInfoPage";
}
@RequestMapping("/get")
public String getPrice(HttpServletRequest request, Model m) {
	RouteDao dao1 =new RouteDao();
	 tickets=Integer.parseInt(request.getParameter("tickets"));
	 viewdata.setTickets(tickets);
	 totalprice=dao1.totalPrice(tickets,rate);
	return "redirect:/call";
}
@RequestMapping("/bookingHistory")
public String showBookingHistory(Model m){
	
	List<ViewBookedData> listBookedHistory=dao.setBookedHistory(viewdata.userid);
	m.addAttribute("list",listBookedHistory);
	return "bookingHistory";
}




}



