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
@Table(name="TBL_ROOMS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Room {
	
	@Id
	@Column(name = "ROOM_NO")
	//@GeneratedValue(strategy = GenerationType.)
	//@SequenceGenerator(name = "ROOM_SEQ", sequenceName = "ROOM_SEQ")
	private String roomNo;
	
	@Column(name = "ROOM_NAME")
	private String roomName;
	
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	//@CreationTimestamp
    private Long createDateTime;
 
    //@UpdateTimestamp
    private Long updateDateTime;

}
