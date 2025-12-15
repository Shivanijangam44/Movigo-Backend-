package com.movigo.service;

import java.util.List;

import com.movigo.dto.seatDtos.SeatAvailableDto;
import com.movigo.dto.seatDtos.SeatDto;
import com.movigo.dto.seatDtos.SeatUpdateDto;

public interface SeatService {
	
	public List<SeatAvailableDto> getAvailableSeatsByScreenId(Long screenId);
	
	public List<SeatDto> getSeatsByScreen(Long screenId);
	
	public SeatDto updateSeat(List<SeatUpdateDto> seatUpdateDto,Long seatId);
	
	public void deleteSeat(Long seatId);
	
	public SeatDto bookSeat(Long seatId);
	
	public SeatDto cancelSeat(Long seatId);
	
}
