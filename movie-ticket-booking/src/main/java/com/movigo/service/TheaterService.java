package com.movigo.service;

import java.util.List;

import com.movigo.dto.theaterDtos.TheaterDto;
import com.movigo.dto.theaterDtos.TheaterUpdateDto;

public interface TheaterService {
	public TheaterDto addTheater(TheaterDto theaterDto);
	
	public List<TheaterDto> getAllTheaters();
	
	public void deleteTheater(Long theaterId);
	
	public List<TheaterDto> getTheaterByName(String TheaterName);
	
	public List<TheaterDto> getTheaterByLocation(String Location);
	
	public TheaterDto updateTheaterDetails(List<TheaterUpdateDto> theaterUpdateDto , Long TheaterId);
	
	
	
}
