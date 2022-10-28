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
@Table(name="TBL_COMMAND_TYPE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Command {

	@Id
	@Column(name = "COMND_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMND_ID_SEQ")
	@SequenceGenerator(name = "COMND_ID_SEQ", sequenceName = "COMND_ID_SEQ")
	private Long commandId;
	
	@Column(name="COMND_TYPE")
	private String commandName;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="COMND_TYPE_CHINISE")
	private String commandNameChinise;
	
	@CreationTimestamp
    private LocalDateTime createDateTime;
 
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
	
}
