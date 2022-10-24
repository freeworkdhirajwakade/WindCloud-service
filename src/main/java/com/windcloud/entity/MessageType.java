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
@Table(name="TBL_MESSAGE_TYPE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MessageType 
{
	@Id
	@Column(name = "MSG_TYPE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MSG_TYP_SEQ")
	@SequenceGenerator(name = "MSG_TYP_SEQ", sequenceName = "MSG_TYP_SEQ")
	private Long msgTypeId;
	
	@Column(name="MSG_TYPE")
	private String msgType;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@CreationTimestamp
    private LocalDateTime createDateTime;
 
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

}
