package com.windcloud.entity;

import java.math.BigDecimal;
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

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	private Long betId;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "BET_DET_ID")
	@JsonBackReference
	private BetDetails betDetails;
	
	@Column(name = "ODDS")
	private Float odds;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "RANK_ID")
	private Ranks rank;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "BET_TYPE_ID")
	private BetType betType;
	
	@Column(name = "CAR_NO")
	private Integer carNumber;
	
	@Column(name = "AMOUNT")
	private Double amount;
	
	@Column(name = "WIN_AMOUNT")
	private Double winAmount;
	
	@Column(name = "WIN_OR_LOSS")
	private String winOrLoss;
	
	@Column(name = "STATUS")
	private String status;
	
	@CreationTimestamp
    private LocalDateTime createDateTime;
 
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

}
