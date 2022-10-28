package com.windcloud.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	private Float odds;
	
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "TBL_BETDETAILS_BET",
        joinColumns = @JoinColumn(name = "BET_DET_ID"),
        inverseJoinColumns = @JoinColumn(name = "BET_ID")
    )
    private Set<Bet> bets = new HashSet<>(); 
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "COMND_ID")
	private Command commonds;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "BET_TYPE_ID")
	private BetType betType;
	
	@Column(name = "STATUS")
	private String status;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "ROOM_NO")
	private Room room;
	
	@CreationTimestamp
    private LocalDateTime createDateTime;
 
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
	
}
