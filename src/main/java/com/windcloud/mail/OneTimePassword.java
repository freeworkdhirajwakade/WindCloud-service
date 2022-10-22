package com.windcloud.mail;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="TBL_OTP")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OneTimePassword {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "OTP_ID")
	private Long otpId;
	
	@Column(name = "OTP")
	private String otp;
	
	@Column(name = "OTP_TYPE")
	private String otpType;
	
	@Column(name = "MAIL_ID")
	private String emailId;
	
	@Column(name = "CREATE_DT_TM", updatable = false)
	@CreationTimestamp
	private LocalDateTime createTime;
	
	@Column(name = "EXP_DT_TM", updatable = false)
	private LocalDateTime expirationTime;
	

}
