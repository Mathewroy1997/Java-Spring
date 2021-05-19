package com.projects11;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projects11.Customer;
import com.projects11.CustomerDao;

@Controller
public class LoginController {
@Autowired 
CustomerDao dao;
@RequestMapping("/login")
public String login(HttpServletRequest request,HttpServletResponse response) {

String username=request.getParameter("username");
String password=request.getParameter("password");
List<Customer>list=dao.getData(username,password);
if(list.isEmpty()) {

return "error.jsp";
}
else {

return "welcome.jsp";
}
}


}