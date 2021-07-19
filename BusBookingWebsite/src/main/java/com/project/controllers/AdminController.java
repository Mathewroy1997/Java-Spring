package com.project.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.models.AdminData;
import com.project.models.BusCategory;
import com.project.models.BusDetails;
import com.project.models.CompleteBookingDetails;
import com.project.models.Customer;

import com.project.models.RouteDetails;

import com.project.models.TripDetails;
import com.project.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	AdminService adminService;

	@RequestMapping("updateBusDetails")
	public String updateBusDetails(Model model) {

		List<BusDetails> busDetailsList = adminService.getBusDetails();
		List<BusCategory> busCategoryList = adminService.getBusCategories();

		model.addAttribute("busDetailsList", busDetailsList);
		model.addAttribute("busCategoryList", busCategoryList);

		return "adminModifyBus";
	}

	@RequestMapping(value = "addNewBus", method = RequestMethod.POST)
	public String addNewBus(HttpServletRequest request, Model m) {
		BusDetails bus = new BusDetails();

		bus.setBusType(request.getParameter("busType"));
		bus.setBusRatePerKm(Integer.parseInt(request.getParameter("ratePerKm")));
		bus.setBusTotalSeats(Integer.parseInt(request.getParameter("seats")));

		try {
			adminService.addNewBus(bus);

			return "redirect:/updateBusDetails";
		} catch (Exception e) {
			return "adminInvalidEntries";
		}

	}

	@RequestMapping("addNewBusFromNewCategory")
	public String addNewBusFromNewCategory() {
		return "adminNewCategoryBus";
	}

	@RequestMapping(value = "newCategoryBus", method = RequestMethod.POST)
	public String newCategoryBus(HttpServletRequest request) {
		BusDetails bus = new BusDetails();
		String category = request.getParameter("category");
		bus.setBusType(request.getParameter("category"));
		bus.setBusRatePerKm(Integer.parseInt(request.getParameter("ratePerKm")));
		bus.setBusTotalSeats(Integer.parseInt(request.getParameter("seats")));
		try {
			adminService.addNewBusCategory(category);
			adminService.addNewBus(bus);

			return "redirect:/updateBusDetails";
		} catch (Exception e) {
			return "adminInvalidEntries";
		}
	}

	@RequestMapping(value = "deleteBus", method = RequestMethod.POST)
	public String deleteBus(HttpServletRequest request) {
		int busId = Integer.parseInt(request.getParameter("busId"));
		adminService.deleteBus(busId);

		return "redirect:/updateBusDetails";
	}

	@RequestMapping(value = "editBus")
	public String editBusDetails(HttpServletRequest request, Model model) {
		int busId = Integer.parseInt(request.getParameter("busId"));
		model.addAttribute("busId", busId);
		return "adminEditBus";
	}

	@RequestMapping(value = "changeBusDetails", method = RequestMethod.POST)
	public String changeBusDetails(HttpServletRequest request) {
		int busId = Integer.parseInt(request.getParameter("busId"));
		int ratePerKm = Integer.parseInt(request.getParameter("ratePerKm"));
		int seats = Integer.parseInt(request.getParameter("seats"));
		try {
			adminService.changeBusDetails(busId, ratePerKm, seats);
			return "redirect:/updateBusDetails";
		} catch (Exception e) {
			return "adminInvalidEntries";
		}
	}

	@RequestMapping("updateRouteDetails")
	public String updateRouteDetails(Model model) {

		List<RouteDetails> routeDetailsList = adminService.getRouteDetails();

		model.addAttribute("routeDetailsList", routeDetailsList);

		return "adminModifyRoute";
	}

	@RequestMapping(value = "deleteRouteDetails", method = RequestMethod.POST)
	public String deleteRouteDetails(HttpServletRequest request) {
		int routeId = Integer.parseInt(request.getParameter("routeId"));
		adminService.deleteRouteDetails(routeId);
		return "redirect:/updateRouteDetails";
	}

	@RequestMapping(value = "addNewRouteDetails", method = RequestMethod.POST)
	public String addNewRouteDetails(HttpServletRequest request) {

		String departure = request.getParameter("departure");
		String destination = request.getParameter("destination");
		int totalDistanceInKm = Integer.parseInt(request.getParameter("totalKm"));
		try {
			adminService.addNewRouteDetails(departure, destination, totalDistanceInKm);
			return "redirect:/updateRouteDetails";
		} catch (Exception e) {
			return "adminInvalidEntries";
		}
	}

	@RequestMapping(value = "updateTripDetails")
	public String updateTripDetails(HttpServletRequest request, Model model) {
		List<TripDetails> tripDetailsList = adminService.getTripDetails();
		List<RouteDetails> routeDetailsList = adminService.getRouteDetails();
		List<BusDetails> busDetailsList = adminService.getBusDetails();

		model.addAttribute("routeDetailsList", routeDetailsList);
		model.addAttribute("busDetailsList", busDetailsList);

		model.addAttribute("tripDetailsList", tripDetailsList);

		return "adminModifyTrip";
	}

	@RequestMapping(value = "addNewTripDetails", method = RequestMethod.POST)
	public String addNewTripDetails(HttpServletRequest request, Model model) {
		String date = request.getParameter("date");
		int routeId = Integer.parseInt(request.getParameter("routeId"));
		int busId = Integer.parseInt(request.getParameter("busId"));
		int availableSeats = Integer.parseInt(request.getParameter("seats"));
		try {
			adminService.addNewTripDetails(date, routeId, busId, availableSeats);
			return "redirect:/updateTripDetails";
		} catch (Exception e) {
			return "adminInvalidEntries";
		}
	}

	@RequestMapping(value = "deleteTripDetails", method = RequestMethod.POST)
	public String deleteTripDetails(HttpServletRequest request) {
		int tripId = Integer.parseInt(request.getParameter("tripId"));
		try {
			adminService.deleteTripDetails(tripId);

			return "redirect:/updateTripDetails";
		} catch (Exception e) {
			return "adminInvalidEntries";
		}
	}

	@RequestMapping("updateAdmin")
	public String updateAdmin(Model adminModel, Model model) {
		List<AdminData> adminList = adminService.getAdminData();
		adminModel.addAttribute("adminList", adminList);
		return "adminModifyAdmin";
	}

	@RequestMapping("admin/{adminData.adminID}")
	public String deleteAdmin(HttpServletRequest request) {
		String path = request.getServletPath();
		int adminID = Integer.parseInt(path.replaceAll("[\\D]", ""));
		adminService.deleteAdmin(adminID);

		return "redirect:/updateAdmin";
	}

	@RequestMapping(value = "addAdmin", method = RequestMethod.GET)
	public String addNewAdmin(HttpServletRequest request, Model model) {
		String newAdminName = request.getParameter("newAdminName");
		String newUsername = request.getParameter("newAdminUsername");
		String newPassword = request.getParameter("newAdminPassword");
		try {
			adminService.insertNewAdmin(newAdminName, newUsername, newPassword);

			return "redirect:/updateAdmin";
		} catch (DuplicateKeyException duplicate) {
			return "adminInvalidEntries";
		}
	}

	@RequestMapping("/manageUsers")
	public String manageUsers(Model usersList) {
		List<Customer> userData = adminService.getCustomerData();

		usersList.addAttribute("userData", userData);
		return "adminUserManagement";
	}

	@RequestMapping("customer/{customer.userid}")
	public String deleteUser(HttpServletRequest request) {
		String path = request.getServletPath();
		int userId = Integer.parseInt(path.replaceAll("[\\D]", ""));
		adminService.deleteCustomer(userId);

		return "redirect:/manageUsers";
	}

	@RequestMapping("addUserFromAdmin")
	public String addUserFromAdmin(HttpServletRequest request) {

		String username = request.getParameter("username");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("emailID");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		try {
			adminService.setUserDetails(username, firstName, lastName, email, address, phone, password);
			return "redirect:/manageUsers";
		} catch (Exception e) {
			return "adminInvalidEntries";
		}

	}

	@RequestMapping("adminBookingHistory")
	public String bookingHistory(Model model) {
		List<CompleteBookingDetails> bookingDetailsList = adminService.getBookingHistory();
		model.addAttribute("bookingDetailsList", bookingDetailsList);
		return "adminBookingHistory";
	}

}
