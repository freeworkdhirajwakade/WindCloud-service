package com.windcloud.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

public interface MailService 
{
	public void sendMail(String from, String to, String content, String subject) throws MessagingException;
	
	public String registerOtpEmail(String email) throws MessagingException;
	
	public void sendForgotPasswordLink(String email) throws UnsupportedEncodingException, MessagingException;

}
