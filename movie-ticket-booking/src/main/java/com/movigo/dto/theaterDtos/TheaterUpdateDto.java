package com.movigo.dto.theaterDtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class TheaterUpdateDto {
	
	public String fieldName;
	
	public String value;
}
