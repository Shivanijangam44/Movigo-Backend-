package com.movigo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.movigo.dto.seatDtos.SeatAvailableDto;
import com.movigo.dto.seatDtos.SeatDto;
import com.movigo.dto.seatDtos.SeatUpdateDto;
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
	@ResponseStatus(HttpStatus.OK)
	public List<SeatDto> getSeatsByScreen(@PathVariable Long screenId){
		return seatService.getSeatsByScreen(screenId);
	}
	
	@DeleteMapping("/delete/{seatId}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteSeat(@PathVariable Long seatId) {
		seatService.deleteSeat(seatId);
	}
	
	@PatchMapping("/update/{seatId}")
	public ResponseEntity<Object> updateSeat(@RequestBody List<SeatUpdateDto> seatUpdateDto, @PathVariable Long seatId){
		SeatDto dto = seatService.updateSeat(seatUpdateDto, seatId);
		
		if(dto != null) {
			return new ResponseEntity<Object>(dto, HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>("Seat not found for Id : " +seatId, HttpStatus.NOT_FOUND);
			
		}
		
	}
	
	@PutMapping("/book/{seatId}")
	@ResponseStatus(HttpStatus.OK)
	public SeatDto bookSeat(@PathVariable Long seatId) {
		return seatService.bookSeat(seatId);
	}
	
	@PutMapping("/cancel/{seatId}")
	@ResponseStatus(HttpStatus.OK)
	public SeatDto cancelSeat(@PathVariable long seatId) {
		return seatService.cancelSeat(seatId);
	}

}
