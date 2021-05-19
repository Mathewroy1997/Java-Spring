package com.javatpoint;  
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;  
import java.sql.*;
@Controller  
public class HelloController { 
	
@RequestMapping("/add")  
    public ModelAndView display(HttpServletRequest request, HttpServletRequest response)  
    		throws Exception {  
    
	String url="jdbc:mysql://localhost:3306/userlog";
	String uname="root";
	String pass="1234";
	String query="SELECT * FROM userlog.data";
	
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection(url,uname,pass);
	Statement st=con.createStatement();
	ResultSet rs= st.executeQuery(query);

	
    ArrayList<String> un= new ArrayList<String>();
    ArrayList<String> pw= new ArrayList<String>();
    
    while(rs.next()) {
    	String users=rs.getString("username");
    	String passwords=rs.getString("password");
    	un.add(users);
    	pw.add(passwords);
    }
    
    
    
    String username=request.getParameter("t1");
    String password=request.getParameter("t2");
    String res = null ;
 
    for(int i=0;i<3;i++) {
    	if(username.contentEquals(un.get(i)) && password.equals(pw.get(i))) {
    	   res="Welcome: "+username; 
    	   break ;
    	}
    	else {
    		res="Invalid credentials..";
    	}
    }
    
    
    
    ModelAndView mv=new ModelAndView();
    mv.setViewName("display");
	mv.addObject("Result", res);
   	return mv;
   	
        
}
}  