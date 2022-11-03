package com.windcloud.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="TBL_RANK")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ranks 
{
	@Id
	@Column(name = "RANK_NO")
	private Long rankId;
	
	@Column(name = "RANK_NAME")
	private String rankName;
	
	
	@Column(name = "RANK_CHINISE_NAME")
	private String rankChiniseName;
	
	@CreationTimestamp
    private LocalDateTime createDateTime;
 
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

}
