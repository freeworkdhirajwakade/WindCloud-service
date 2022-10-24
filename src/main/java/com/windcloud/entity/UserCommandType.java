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
@Table(name="TBL_USER_COMMAND_TYPE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCommandType {

	@Id
	@Column(name = "USER_COMND_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_COMND_ID_SEQ")
	@SequenceGenerator(name = "USER_COMND_ID_SEQ", sequenceName = "USER_COMND_ID_SEQ")
	private Long userCommandId;
	
	@Column(name="USER_COMND_TYPE")
	private String UserCommandType;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="USER_COMND_TYPE_CHINISE")
	private String userCommandTypeChinise;
	
	@CreationTimestamp
    private LocalDateTime createDateTime;
 
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
	
}
