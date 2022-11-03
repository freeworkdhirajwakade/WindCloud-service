package com.windcloud.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="TBL_GAME")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Game {
	
	@Id
	@Column(name = "GAME_ID")
	///@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GAME_SEQ")
	//@SequenceGenerator(name = "GAME_SEQ", sequenceName = "GAME_SEQ")
	//private Long gameId;
	private Long gameId;
	
	@Column(name="GAME_NAME")
	private String gameName;
	
	@Column(name="IS_GAME_OPEN")
	private Boolean isGameOpen;
	
	@OneToMany(mappedBy = "game")
	private Set<RankDetails> rankdDetails;
	
	@OneToMany(mappedBy = "game")
	@JsonManagedReference
	private Set<BetDetails> betDetails;
	
	@Column(name="OPEN_TIME")
	private Long openTime;
	
	@Column(name="DURATTION")
	private Integer duration;
	
	@Column(name="CLOSE_TIME")
	private Long closeTime;
	
	@Column(name="SCHEDULER_TASK_STATUS")
	private Boolean schedulerTaskStatus;
	
	@Column(name="BETTING_LIMIT")
	private BigDecimal bettingLimit;
	
	@Column(name="IS_CANCEL_ALLOW")
	private Boolean isCancellationAllow;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="AVATAR")
	private String avatar;
			
	@CreationTimestamp
    private LocalDateTime createDateTime;
 
    @UpdateTimestamp
    private LocalDateTime updateDateTime;	

}
