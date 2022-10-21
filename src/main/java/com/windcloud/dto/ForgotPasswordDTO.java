package com.windcloud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordDTO 
{
	private String newPassword;
	private String confirmPassword;
	private String email;

}
