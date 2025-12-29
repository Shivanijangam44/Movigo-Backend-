package com.movigo.dto.showDtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	@JsonFormat(pattern = "hh:mm a")
	private LocalDateTime showTime;
	
	private Long movieId;
	
	private String movieName;
	
	private Long screenId;
}
