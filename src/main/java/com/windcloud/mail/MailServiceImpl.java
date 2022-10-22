package com.windcloud.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.windcloud.config.CustomerProperties;
import com.windcloud.utils.Util;

@Service("mailService")
public class MailServiceImpl implements MailService {

	Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

	@Autowired
	private CustomerProperties properties;

	@Autowired
	private OneTimePasswordService otpService;

	@Autowired
	JavaMailSender mailSender;

	public void sendMail(String from, String to, String content, String subject) throws MessagingException
	{
		Mail mail = new Mail();
		mail.setMailFrom(from);
		mail.setMailTo(to);
		mail.setMailSubject(subject);
		mail.setMailContent(content);
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		//mimeMessage.setContent(content,"text");
		try {

			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setSubject(mail.getMailSubject());
			mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom(), properties.getWebsiteName()));
			mimeMessageHelper.setTo(mail.getMailTo());
			mimeMessageHelper.setText(mail.getMailContent(),true);
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

	@Override
	public String registerOtpEmail(String email) throws MessagingException {

		String opt = Util.generateSixDigitOTP();
		String content = properties.getEmailVeritificationContent().replace("OTP_VALUE", opt);
		content = content.replace("TIME_IN_MIN", properties.getEmailVerifExpTime().toString());

		sendMail(properties.getMailFrom(), email, content, properties.getMailSubject());

		try {
			OneTimePassword saveOpt = new OneTimePassword();
			saveOpt.setEmailId(email);
			saveOpt.setOtp(opt);
			saveOpt.setOtpType("EMAIL_VERIFICATION");
			saveOpt.setExpirationTime(Util.nextMinute(properties.getEmailVerifExpTime()));
			otpService.saveOrUpdateOTP(saveOpt);
			return opt;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.info(e.getCause().toString());
			e.printStackTrace();

			return null;
		}
	}

	public void sendForgotPasswordLink(String email) throws UnsupportedEncodingException, MessagingException {

		String token = Util.encodeValue(Util.nextMinute(properties.getForgotPassLinkExpTime()).toString());
		String link = properties.getForgotPasswordLink().replace("EMAIL", email);
		link = link.replace("TOKEN", token);
		String content = properties.getForgotPasswordContent().replace("LINK", link);

		sendMail(properties.getMailFrom(), email, content, properties.getForgotPasswordSubject());
	}

}