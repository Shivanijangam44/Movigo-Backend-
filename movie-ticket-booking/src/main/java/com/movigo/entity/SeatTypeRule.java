package com.movigo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class SeatTypeRule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seatTypeRuleId;
	
	@Column(nullable = false, length = 1)
	private String startRow;
	
	@Column(nullable = false, length = 1)
	private String endRow;
	
	@Column(nullable = false)
	private String seatType;
	
	@ManyToOne
	@JoinColumn(name = "theater_id", nullable = false)
	private Theater theater;
}
