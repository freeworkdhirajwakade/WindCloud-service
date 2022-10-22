package com.windcloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController 
{
	
    @GetMapping("/home/forgot_password")
    public String showForgotPasswordForm() 
    {
    	 return "forgotpassword"; 
    }
    @GetMapping("/")
    public String home() 
    {
    	 return "home"; 
    }
    
    @GetMapping("/home/setPassword/{email}/{token}")
    public ModelAndView updatePassword(@PathVariable String email,@PathVariable String token)
    {
    	ModelAndView modelAndView = new ModelAndView("setPassword");
        modelAndView.addObject("email",email);
        modelAndView.addObject("token",token);
        return modelAndView;
    }

}
