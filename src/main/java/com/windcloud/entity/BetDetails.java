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
@Table(name="TBL_BET_DETAILS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BetDetails 
{
	@Id
	@Column(name = "BET_DET_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BET_D_SEQ")
	@SequenceGenerator(name = "BET_D_SEQ", sequenceName = "BET_D_SEQ")
	private Long betDetId;
	
	@Column(name = "ODDS")
	private Long odds;
	
	@Column(name = "COMMAND")
	private String command;
	
	@Column(name = "STATUS")
	private String status;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "ROOM_ID")
	private Room room;
	
	@CreationTimestamp
    private LocalDateTime createDateTime;
 
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
    
    
	
}
