package com.project.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)

	public String login(HttpServletRequest request, Model model) {

		String username = request.getParameter("username");

		String password = request.getParameter("password");

		boolean myCheckBox = request.getParameter("adminCheck") != null;

		if (myCheckBox == true) {

			int flag = loginService.adminLogin(username, password);

			if (flag == 0) {
				String error = "Invalid Credentials";

				model.addAttribute("error", error);

				return "invalidLoginAttempt";
			} else {

				return "adminHome";
			}

		}

		int flag = loginService.loginAsUser(username, password);

		if (flag == 0) {
			String error = "Invalid Credentials";

			model.addAttribute("error", error);

			return "invalidLoginAttempt";
		}

		else {
			int userId = loginService.getUserId(username, password);

			model.addAttribute("username", username);
			model.addAttribute("userId", userId);

			return "userHome";
		}
	}

	@RequestMapping("signUp")
	public String registrationPage() {
		return "Register";
	}

	@RequestMapping(value = "logout")
	public String backToIndex() {
		return "index";
	}

	@RequestMapping(value = "backToUserHome")
	public String backToUserHome(HttpServletRequest request, Model model) {
		int userId = Integer.parseInt(request.getParameter("userId"));
		String userName = request.getParameter("username");
		model.addAttribute("username", userName);
		model.addAttribute("userId", userId);
		return "userHome";
	}

	@RequestMapping(value = "backToAdminHome")
	public String backToAdminHome() {
		return "adminHome";
	}

}
