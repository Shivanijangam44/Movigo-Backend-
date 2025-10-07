package com.movigo.service;

import java.util.List;

import com.movigo.dto.movieDtos.MovieDto;
import com.movigo.dto.movieDtos.MovieUpdateDto;

public interface MovieService {

	public MovieDto addMovie(MovieDto movieDto);
	
	public void deleteMovie(Long movieId);
	
	public List<MovieDto> getAllMovies();
	
	public List<MovieDto> getMovieByMovieName(String MovieName );
	
	public MovieDto updateMovieDetails(List<MovieUpdateDto> movieUpdateDto,Long movieId );
	
	public List<MovieDto> getMovieByGenre(String Genre);
	
	public List<MovieDto> getMovieByLanguage(String Language);


	
	
}
