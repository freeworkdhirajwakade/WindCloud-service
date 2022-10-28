package com.windcloud.entity;

import java.math.BigDecimal;
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
@Table(name="TBL_GAME")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Game {
	
	@Id
	@Column(name = "GAME_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GAME_SEQ")
	@SequenceGenerator(name = "GAME_SEQ", sequenceName = "GAME_SEQ")
	private Long gameId;
	
	@Column(name="GAME_NAME")
	private String gameName;
	
	@Column(name="IS_GAME_OPEN")
	private Boolean isGameOpen;
	
	@Column(name="OPEN_TIME")
	private Long openTime;
	
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
	
	@CreationTimestamp
    private LocalDateTime createDateTime;
 
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
	

}
