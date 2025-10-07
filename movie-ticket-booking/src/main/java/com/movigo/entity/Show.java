package com.movigo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "shows")
public class Show extends BaseEntity{
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long showId;
	
    @Column(nullable = false)
	private LocalDateTime showTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "movie_id" , nullable = false)
	private Movie movie;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id", nullable = false)
    private Screen screen;
	
}
