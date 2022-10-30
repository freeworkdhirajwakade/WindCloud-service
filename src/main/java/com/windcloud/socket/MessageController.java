package com.windcloud.socket;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessageController {
   
	@Autowired
	private NotificationService notificationService;
	
	   
    @GetMapping("/chat")
    public String chat() 
    {
    	 return "chat"; 
    }
    
    @GetMapping("/chatroom")
    public String chatroom() 
    {
    	 return "chatroom"; 
    }
	
	
	@MessageMapping("/message")
    @SendTo("/topic/messages")
    public ResponseMessage getMessage(final Message message) throws InterruptedException {
        Thread.sleep(1000);
       notificationService.sendGlobalNotification();
        return new ResponseMessage(message.getContent(),message.getUsername());
    }

    @MessageMapping("/private-message")
    @SendToUser("/topic/private-messages")
    public ResponseMessage getPrivateMessage(final Message message,
                                             final Principal principal) throws InterruptedException {
        Thread.sleep(1000);
        notificationService.sendPrivateNotification(principal.getName());
       /* return new ResponseMessage(HtmlUtils.htmlEscape(
                "Sending private message to user " + principal.getName() + ": "
                        + message.getContent())
        );*/
        return null;
    }
}
