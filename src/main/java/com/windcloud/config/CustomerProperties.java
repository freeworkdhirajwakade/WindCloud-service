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
	
	@Value("${mail.website.name}")
	private String websiteName;

}
