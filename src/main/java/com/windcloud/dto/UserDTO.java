package com.windcloud.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO
{
	private Long userId;
	
	private String name;
	
	private String nickName;
	
	private String password;
	
	private String email;
	
	private String gender;
	
	private String phone;
	
	private String authCode;
	
	private String avatar;
	
	private String status;
	
    private LocalDateTime createDateTime;
 
    private LocalDateTime updateDateTime;
}