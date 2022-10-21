package com.windcloud.mail;

public interface MailService 
{
	public void sendEmail(Mail mail);
	
	public String registerOtpEmail(String email);

}
