package com.movigo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.movigo.dto.seatDtos.SeatAvailableDto;
import com.movigo.dto.seatDtos.SeatDto;
import com.movigo.service.SeatService;

@RestController
@RequestMapping("api/seat/")
public class SeatController {
	
	@Autowired
	private SeatService seatService;
	
	@GetMapping("/available/{screenId}")
	@ResponseStatus(HttpStatus.OK)
	public List<SeatAvailableDto> getAvailableSeat(@PathVariable Long screenId){
		return seatService.getAvailableSeatsByScreenId(screenId);
	}
	
	@GetMapping("/search/{screenId}")
	public List<SeatDto> getSeatsByScreen(@PathVariable Long screenId){
		return seatService.getSeatsByScreen(screenId);
	}
	

}
