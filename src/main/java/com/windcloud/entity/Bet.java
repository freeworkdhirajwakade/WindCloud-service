package com.windcloud.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="TBL_BET")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Bet {
	
	@Id
	@Column(name = "BET_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BET_SEQ")
	@SequenceGenerator(name = "BET_SEQ", sequenceName = "BET_SEQ")
	private Long betTypeId;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "USER_ID")
	private User user;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "BET_TYPE_ID")
	private BetType betType;
	
	@Column(name = "CAR_NO")
	private String carNumber;
	
	@Column(name = "AMOUNT")
	private Double amount;
	
	@Column(name = "STATUS")
	private String status;
	
	@CreationTimestamp
    private LocalDateTime createDateTime;
 
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

}
