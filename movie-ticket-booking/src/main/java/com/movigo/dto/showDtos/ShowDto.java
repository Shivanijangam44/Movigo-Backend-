package com.movigo.dto.showDtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ShowDto {
	
	private Long showId;
	
	private LocalDateTime showTime;
	
	private Long movieId;
	
	private String movieName;
	
	private Long screenId;
}
