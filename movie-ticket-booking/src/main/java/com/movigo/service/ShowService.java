package com.movigo.service;

import java.util.List;

import com.movigo.dto.showDtos.ShowCreateDto;
import com.movigo.dto.showDtos.ShowDto;

public interface ShowService {
	
	public ShowDto addShow(ShowCreateDto showCreateDto);
	
	public void deleteShow(Long showId);
	
	public ShowDto updateShow();
	
	public List<ShowDto> getShowByMovie(String movieName);
	
	public ShowDto getShowById(Long showId);
	
	public List<ShowDto> getShowByScreen(Long screenId);
	
}
