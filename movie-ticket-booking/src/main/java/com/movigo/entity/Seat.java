package com.movigo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@Table(name = "seats")
public class Seat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seatId;
	
	@Column(nullable = false)
	private String seatNumber;
	
	@Column(nullable = false)
	private String seatType;
	
	private boolean isAvailable = true;
	
	@ManyToOne
    @JoinColumn(name = "screen_id", nullable = false)
    private Screen screen;
	
	
	
}
