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
@Table(name="TBL_TRAN_TYPE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionType 
{
	@Id
	@Column(name = "TRAN_TYPE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRAN_TY_SEQ")
	@SequenceGenerator(name = "TRAN_TY_SEQ", sequenceName = "TRAN_TY_SEQ")
	private Long tranTypeId;
	
	@Column(name = "TRAN_TYPE")
	private String tranType;
	
	@Column(name = "TRAN_TYPE_DESC")
	private String description;
	
	@CreationTimestamp
    private LocalDateTime createDateTime;
 
    @UpdateTimestamp
    private LocalDateTime updateDateTime;


}
