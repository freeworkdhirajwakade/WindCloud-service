package com.windcloud.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OneTimePasswordService {
	
	@Autowired
	private OneTimePasswordRepository oneTimePasswordRepository;
	
	@Autowired
	private MailService mailService;
	
	public String sendOTP(String mailId)
	{
		return mailService.registerOtpEmail(mailId);
	}
	
	public OneTimePassword saveOrUpdateOTP(OneTimePassword otpnew)
	{
		OneTimePassword existingotp = oneTimePasswordRepository.findByEmail(otpnew.getEmailId());

		if (existingotp == null) {
			OneTimePassword newOtp = new OneTimePassword();
			newOtp.setOtp(otpnew.getOtp());
			newOtp.setEmailId(otpnew.getEmailId());
			return oneTimePasswordRepository.save(newOtp);
		} else {
			existingotp.setOtp(otpnew.getOtp());
			return oneTimePasswordRepository.saveAndFlush(existingotp);
		}
	}
	

}
