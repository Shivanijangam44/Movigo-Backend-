package com.movigo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.movigo.dto.theaterDtos.TheaterDto;
import com.movigo.dto.theaterDtos.TheaterUpdateDto;
import com.movigo.service.TheaterService;


@RestController
@RequestMapping("api/theaters")
public class TheaterController {
	
	@Autowired
	public TheaterService theaterService;
	
	@PostMapping("/addTheater")
	@ResponseStatus(HttpStatus.CREATED)
	public TheaterDto addTheater(@RequestBody TheaterDto theaterDto) {
		return theaterService.addTheater(theaterDto);
	}
	
	@GetMapping("/getAllTheaters")
	@ResponseStatus(HttpStatus.OK)
	public List<TheaterDto> getAllTheaters(){
		return theaterService.getAllTheaters();
		
	}
	
	@DeleteMapping("/delete/{theaterId}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteTheater(@PathVariable Long theaterId) {
		theaterService.deleteTheater(theaterId);
	}
	
	@GetMapping("/search/theater/{theaterName}")
	@ResponseStatus(HttpStatus.OK)
	public List<TheaterDto> getTheaterByName(@PathVariable String theaterName) {
		return theaterService.getTheaterByName(theaterName);
	}
	
	@GetMapping("/search/location/{location}")
	@ResponseStatus(HttpStatus.OK)
	public List<TheaterDto> getTheaterByLocation(@PathVariable String location){
		return theaterService.getTheaterByLocation(location);
	}
	
	@PatchMapping("/update/{theaterId}")
	public ResponseEntity<Object> updateTheater(@RequestBody List<TheaterUpdateDto> theaterUpdateDto, @PathVariable Long theaterId){
		TheaterDto thDto = theaterService.updateTheaterDetails(theaterUpdateDto, theaterId);
		if(thDto != null) {
		return new ResponseEntity<Object>(thDto, HttpStatus.OK);
		}else
		{
		return new ResponseEntity<Object>("Theater not found for Id "+theaterId,HttpStatus.NOT_FOUND);
		}
	}
}
