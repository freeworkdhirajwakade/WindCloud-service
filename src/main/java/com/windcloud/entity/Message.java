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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="TBL_MESSAGE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Message {
	
	@Id
	@Column(name = "MSG_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MSG_SEQ")
	@SequenceGenerator(name = "MSG_SEQ", sequenceName = "MSG_SEQ")
	private Long msgId;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "FROM_USER_ID")
	private User fromUser;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "TO_USER_ID")
	private User toUser;
	
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "MSG_TYPE_ID")
	private MessageType msgType;
	
	@Column(name="MSG_CONTENT")
	private String content;
	
	@Column(name="STATUS")
	private String status;
	
	@CreationTimestamp
    private LocalDateTime createDateTime;
 
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
	
	

}
