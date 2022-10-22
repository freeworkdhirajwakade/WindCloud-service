package com.windcloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;

@Configuration
@PropertySource("classpath:custom.properties")
@Getter
public class CustomerProperties {
	
	@Value( "${mail.email.verify.content}" )
	private String emailVeritificationContent;
	
	@Value( "${mail.from}" )
	private String mailFrom;
	
	@Value("${mail.subject}" )
	private String mailSubject;
	
	@Value("${mail.forgot.password.otp.expire.min}")
	private Integer emailVerifExpTime;
	
	@Value("${mail.website.name}")
	private String websiteName;
	
	@Value("${mail.forgot.password.subject}")
	private String forgotPasswordSubject;
	
	@Value("${mail.forgot.password.link}")
	private String forgotPasswordLink;
	
	@Value("${mail.forgot.password.content}")
	private String forgotPasswordContent;
	
	@Value("${mail.forgot.password.link.exp.min}")
	private Integer forgotPassLinkExpTime;
	
	
	

}
