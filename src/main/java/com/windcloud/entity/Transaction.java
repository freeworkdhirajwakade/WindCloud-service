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
@Table(name="TBL_TRANSACTION")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
	
	@Id
	@Column(name = "TRAN_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRAN_SEQ")
	@SequenceGenerator(name = "TRAN_SEQ", sequenceName = "TRAN_SEQ")
	private Long tranId;
	
	@Column(name = "TRAN_REF_NO",unique = true)
	private String tranRefNo;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "USER_ID")
	private User user;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "TRAN_TYPE_ID")
	private TransactionType tranType;
	
	@Column(name = "AMOUNT")
	private BigDecimal amount;
	
	@Column(name = "Fee")
	private Double fee;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "COMMAND")
	private String command;
	
	@CreationTimestamp
    private LocalDateTime createDateTime;
 
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

}
