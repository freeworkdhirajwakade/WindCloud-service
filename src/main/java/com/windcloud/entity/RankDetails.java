package com.windcloud.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="TBL_RANK_DETAILS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RankDetails
{
	@Id
	@Column(name = "RANK_DET_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RANK_DET_SEQ")
	@SequenceGenerator(name = "RANK_DET_SEQ", sequenceName = "RANK_DET_SEQ")
	private Long rankDetailsId; 
	
	@OneToOne
	@JoinColumn(name = "RANK_ID")
	private Ranks rank;
	
    @ManyToOne
    @JoinColumn(name="GAME_ID", nullable=false)
	private Game game;
	
	@Column(name = "RANK_VALUE")
	private Integer rankValue;
	
	@CreationTimestamp
    private LocalDateTime createDateTime;
 
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
	
	

}
