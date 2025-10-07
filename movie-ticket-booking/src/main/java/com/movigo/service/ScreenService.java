package com.movigo.service;

import java.util.List;

import com.movigo.dto.screenDtos.ScreenDto;
import com.movigo.dto.screenDtos.ScreenUpdateDto;


public interface ScreenService {
	
	public ScreenDto addScreen(ScreenDto screenDto);
	
	public void deleteScreen(Long screenId);
	
	public List<ScreenDto> getAllScreensByTheateId(Long theaterId);
	
	public ScreenDto updateScreenDetails(List<ScreenUpdateDto> screenUpdateDto,Long screenId);
		
	
}
