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

import com.movigo.dto.movieDtos.MovieDto;
import com.movigo.dto.movieDtos.MovieUpdateDto;
import com.movigo.service.MovieService;

@RestController
@RequestMapping("api/movies")
public class MovieController {
	
	@Autowired
	public MovieService movieService;
	
	@PostMapping("/addMovie")
	@ResponseStatus(HttpStatus.CREATED)
	//@modelattribute for image uploading
	public MovieDto addMovie(@RequestBody MovieDto movieDto) {
		return movieService.addMovie(movieDto);
		
	}
	
	@GetMapping("/getAllMovies")
	@ResponseStatus(HttpStatus.OK)
	public List<MovieDto> getAllMovies(){
		return movieService.getAllMovies();
	}
	
	@PatchMapping("/update/{movieId}")
	public ResponseEntity<Object> updateMovie(@RequestBody List<MovieUpdateDto> movieUpdateDto ,
			@PathVariable Long movieId){
				MovieDto movieDto = movieService.updateMovieDetails(movieUpdateDto, movieId);
				
				if(movieDto !=null) {
					return new ResponseEntity<Object>(movieDto, HttpStatus.OK);
				}
				return new ResponseEntity<Object>("Movie not found for Id : " +movieId, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/search/name/{movieName}")
	@ResponseStatus(HttpStatus.OK)
	public List<MovieDto> getMovieNyMovieName(@PathVariable String movieName){
		return movieService.getMovieByMovieName(movieName);
	}
	
	@DeleteMapping("/delete/{movieId}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteMovie (@PathVariable Long movieId) {
		movieService.deleteMovie(movieId);
	}
	
	@GetMapping("/search/genre/{genre}")
	@ResponseStatus(HttpStatus.OK)
	public List<MovieDto> getMovieByGenre(@PathVariable String genre){
		return movieService.getMovieByGenre(genre);
	}
	
	@GetMapping("/search/language/{language}")
	@ResponseStatus(HttpStatus.OK)
	public List<MovieDto> getMoviesBylanguage(@PathVariable String language){
		return movieService.getMovieByLanguage(language);
	}

}
