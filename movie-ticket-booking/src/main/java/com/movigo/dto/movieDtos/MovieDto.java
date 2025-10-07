package com.movigo.dto.movieDtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MovieDto {

    private String movieName;
	
    private String genre;

    private int duration;
	
    private String language;
    
    private String description;
    
	private LocalDate releaseDate;
	
	private String imageUrl;
	

	
}
