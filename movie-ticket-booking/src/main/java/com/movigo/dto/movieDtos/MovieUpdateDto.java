package com.movigo.dto.movieDtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class MovieUpdateDto {

	private String fieldName;
	
	private String value;
}
