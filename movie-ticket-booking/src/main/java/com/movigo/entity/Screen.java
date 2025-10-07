package com.movigo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Screen extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long screenId;
	
    @Column(nullable = false)
	private String screenName;
	
    @Column(nullable = false)
	private int totalSeats;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater;
	
    @OneToMany(mappedBy = "screen",cascade = CascadeType.ALL, orphanRemoval = true )
	private List<Show> show;
}
