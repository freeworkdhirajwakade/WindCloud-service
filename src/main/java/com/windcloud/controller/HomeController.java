package com.windcloud.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import com.windcloud.socket.Greetings;
import com.windcloud.socket.HelloMessage;

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
    @MessageMapping("/hello")
    @SendTo("/topic/greeting")
    public Greetings sayHello(HelloMessage mesage)
    {
    	return new Greetings("Hello , "+HtmlUtils.htmlEscape(mesage.getName()));
    }
    
    
    @GetMapping("/home/chat")
    public String chat() 
    {
    	 return "chat"; 
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
