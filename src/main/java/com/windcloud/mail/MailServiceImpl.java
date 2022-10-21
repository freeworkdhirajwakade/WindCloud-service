package com.windcloud.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.windcloud.config.CustomerProperties;
import com.windcloud.constants.CommanConstants;

@Service("mailService")
public class MailServiceImpl implements MailService {

	Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

	@Autowired
	private CustomerProperties properties;
	
	@Autowired
	private OneTimePasswordService otpService;

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	private OneTimePasswordRepository oneTimePasswordRepository;

	public void sendEmail(Mail mail) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		// mimeMessage.setContent("text/html");
		try {

			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setSubject(mail.getMailSubject());
			mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom(), properties.getWebsiteName()));
			mimeMessageHelper.setTo(mail.getMailTo());
			mimeMessageHelper.setText(mail.getMailContent());
			mailSender.send(mimeMessageHelper.getMimeMessage());

		} catch (MessagingException e) {

			logger.info(e.getMessage());
			logger.info(e.getCause().toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			logger.info(e.getCause().toString());
		}
	}

	public void setMailProperty(String from, String to, String content, String subject) {
		Mail mail = new Mail();
		mail.setMailFrom(from);
		mail.setMailTo(to);
		mail.setMailSubject(subject);
		mail.setMailContent(content);
		sendEmail(mail);
	}

	@Override
	public String registerOtpEmail(String email) {

		String opt = generateOTP();
		String content=properties.getEmailVeritificationContent().replace("OTP_VALUE", opt);
		content = content.replace("TIME_IN_MIN","5");
		
		setMailProperty(properties.getMailFrom(),email,content,properties.getMailSubject());
		
		try {
			
			OneTimePassword saveOpt=new OneTimePassword();
			saveOpt.setEmailId(email);
			saveOpt.setOtp(opt);
			saveOpt.setOtpType("EMAIL_VERIFICATION");
			otpService.saveOrUpdateOTP(saveOpt);			
			return opt;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(e.getCause().toString());
			e.printStackTrace();

			return null;
		}
	}
	
	public void sendForgotPasswordLink(String email) {

		String content=properties.getEmailVeritificationContent().replace("OTP_VALUE", "");
		content = content.replace("TIME_IN_MIN","5");
		
		setMailProperty(properties.getMailFrom(),email,content,properties.getMailSubject());
		
		try {
			
			OneTimePassword saveOpt=new OneTimePassword();
			saveOpt.setEmailId(email);
			saveOpt.setOtpType("EMAIL_VERIFICATION");
			otpService.saveOrUpdateOTP(saveOpt);			
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(e.getCause().toString());
			e.printStackTrace();

		}
	}
	
	public static String generateOTP() {
		String time = String.valueOf(System.currentTimeMillis());
		String opt = time.substring(time.length() - 7, time.length() - 1);
		return opt;
	}

}