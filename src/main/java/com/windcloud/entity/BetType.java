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
@Table(name="TBL_BET_TYPE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BetType {
	@Id
	@Column(name = "BET_TYPE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "B_TY_SEQ")
	@SequenceGenerator(name = "B_TY_SEQ", sequenceName = "B_TY_SEQ")
	private Long betTypeId;
	
	@Column(name = "BET_TYPE")
	private String betType;
	
	@Column(name = "BET_TYPE_CHINESE")
	private String betTypeInChinese;
	
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@CreationTimestamp
    private LocalDateTime createDateTime;
 
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

}
