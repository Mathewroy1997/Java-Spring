package com.javatpoint;  
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
@Controller  
public class HelloController {  
@RequestMapping("/add")  
    public String display()  
    {  
        return "display";  
    }     
}  