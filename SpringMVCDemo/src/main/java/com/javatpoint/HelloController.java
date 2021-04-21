package com.javatpoint;  
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;  
@Controller  
public class HelloController {  
@RequestMapping("/add")  
    public ModelAndView display(HttpServletRequest request, HttpServletRequest response)  
    {  
    String str=request.getParameter("t1");
    int i= 0;
    
    for(int j=0;j<str.length();j++) {
    	
    	i++;
    	if(str.charAt(j)==' ' || str.charAt(j)=='.') {
    		i--;
    	}
    }
    
    ModelAndView mv=new ModelAndView();
    mv.setViewName("display");
    mv.addObject("Number",i);
	return mv;  
    }     
}  