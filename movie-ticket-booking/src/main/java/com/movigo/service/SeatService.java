package com.movigo.service;

import java.util.List;

import com.movigo.dto.seatDtos.SeatAvailableDto;
import com.movigo.dto.seatDtos.SeatDto;

public interface SeatService {
	
	public List<SeatAvailableDto> getAvailableSeatsByScreenId(Long screenId);
	
	public List<SeatDto> getSeatsByScreen(Long screenId);
	
	public SeatDto updateSeat(Long seatId, Long screenId);
	
	public void deleteSeat(Long seatId);
	
	public SeatDto bookSeat(Long seatId);
	
	public SeatDto cancelSeat(Long seatId);
	
}
