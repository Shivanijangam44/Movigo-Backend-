package com.movigo.dto.seatDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class SeatAvailableDto {

	private String seatNumber ;
	
	private String seatType;

}
