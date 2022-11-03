package com.windcloud.entity;

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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="TBL_RESULT_DETAILS")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResultDetails {
	
	@Id
	@Column(name = "RESULT_DETAILS_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESULT_DET_SEQ")
	@SequenceGenerator(name = "RESULT_DET_SEQ", sequenceName = "RESULT_DET_SEQ")
	private Long resultDetailsId;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "BET_DET_ID")
	private BetDetails betDetails;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Bet> bet;

	@Column(name="CREATED_AT")
    private Long createDateTime;
 
	@Column(name="UPDATED_AT")
    private Long updateDateTime;
	

}
