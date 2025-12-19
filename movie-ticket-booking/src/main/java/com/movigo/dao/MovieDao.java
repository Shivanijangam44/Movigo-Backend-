package com.movigo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.movigo.entity.Movie;

public interface MovieDao extends JpaRepository<Movie, Long> {
	
	List<Movie> findByMovieName(String movieName);
	
	List<Movie> findByGenre(String genre);

	List<Movie> findByLanguage(String language);
}
