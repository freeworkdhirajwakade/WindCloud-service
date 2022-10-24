package com.windcloud.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.windcloud.entity.Roles;

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
    
	private Boolean isMailVerified;
	
	private Boolean isPhoneVerified;
	
	private BigDecimal points;
    
    private Set<Roles> roles = new HashSet<>();
}
