package com.movigo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.movigo.dto.showDtos.ShowCreateDto;
import com.movigo.dto.showDtos.ShowDto;
import com.movigo.service.ShowService;

@RestController
@RequestMapping("api/shows")
public class ShowController {
	
	@Autowired
	public ShowService showservice;
	
	@PostMapping("/addShow")
	@ResponseStatus(HttpStatus.CREATED)
	public ShowDto addShow(@RequestBody ShowCreateDto showCreateDto) {
		return showservice.addShow(showCreateDto);
	}
	
	@DeleteMapping("/deleteShow/{showId}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteShow(@PathVariable Long showId) {
		showservice.deleteShow(showId);
	}
	
	@GetMapping("/getShow/{showId}")
	@ResponseStatus(HttpStatus.OK)
	public ShowDto getMovieById(@PathVariable Long showId) {
		return showservice.getShowById(showId);
	}
	
	@GetMapping("/search/show/{movieName}")
	@ResponseStatus(HttpStatus.OK)
	public List<ShowDto> getShowByMovie(@PathVariable String movieName){
		return showservice.getShowByMovie(movieName);
	}
}
