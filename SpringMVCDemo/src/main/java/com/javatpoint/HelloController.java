package com.javatpoint;  
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;  
@Controller  
public class HelloController {  
@RequestMapping("/add")  
    public ModelAndView display(HttpServletRequest request, HttpServletRequest response)  
    {  
    
    ArrayList<String> un= new ArrayList<String>();
    ArrayList<String> pw= new ArrayList<String>();
    
    un.add("user1");
    un.add("user2");
    un.add("user3");
    un.add("user4");
    un.add("user5");
    
    pw.add("pwd1");
    pw.add("pwd2");
    pw.add("pwd3");
    pw.add("pwd4");
    pw.add("pwd5");
    
    String username=request.getParameter("t1");
    String password=request.getParameter("t2");
    String res = null ;
    outerloop:
    for(int i=0;i<5;i++) {
    	if(username.contentEquals(un.get(i)) && password.equals(pw.get(i))) {
    	   res="Welcome: "+username; 
    	   break outerloop;
    	}
    	else {
    		res ="Invalid Credentials..";
    	}
    }
    
    
    
    ModelAndView mv=new ModelAndView();
    
    mv.setViewName("display");
    
	mv.addObject("Result", res);
   	return mv;
	
    }     
}  