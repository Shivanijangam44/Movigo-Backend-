package com.movigo.dto.screenDtos;

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
@ToString
@Builder

public class ScreenDto {
	private String screenName;
	
	private int totalSeats;
	
	private Long theaterId;
	
}
