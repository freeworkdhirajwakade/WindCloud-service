package com.windcloud.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="TBL_USER")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ")
	@SequenceGenerator(name = "USERS_SEQ", sequenceName = "USERS_SEQ")
	private Long userId;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "NICKNAME")
	private String nickName;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "PHONE")
	private String phone;
	
	@Column(name = "AUTHCODE")
	private String authCode;
	
	@Column(name = "AVATAR")
	private String avatar;
	
	@Column(name = "STATUS")
	private String status;
	
	@CreationTimestamp
    private LocalDateTime createDateTime;
 
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
	
}
