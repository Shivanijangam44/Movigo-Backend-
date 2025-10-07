package com.movigo.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "movies")
public class Movie extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
    private Long movieId;
	
	@Column(nullable = false)
    private String movieName;
	
	@Column(nullable = false)
    private String genre;
	
	@Column(nullable = false)
    private int duration;
	
	@Column(nullable = false)
    private String language;
    
	@Column(nullable = false)
    private String description;
	
	@Column(name = "release_date", nullable = false)
	private LocalDate releaseDate;
	
	private String imageUrl;
	
	@OneToMany(mappedBy = "movie",cascade = CascadeType.ALL, orphanRemoval = true )
	private List<Show> shows;
	


}
