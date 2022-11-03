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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="TBL_RESULT")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Result 
{
	@Id
	@Column(name = "RESULT_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESULT_SEQ")
	@SequenceGenerator(name = "RESULT_SEQ", sequenceName = "RESULT_SEQ")
	private Long resultId;
	
	@Column(name="CAR_NO")
	private Integer carNumber;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "ROOM_ID")
	private Room room;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "GAME_ID")
	private Game game;
	
	@Column(name="CREATED_AT")
	private Long createdAt;
	
	@Column(name="FINISHED_AT")
	private Long finishedAt;
	
}
