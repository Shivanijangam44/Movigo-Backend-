package com.movigo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.movigo.entity.Movie;

public interface MovieDao extends JpaRepository<Movie, Long> {
	
	@Query("SELECT m FROM Movie m where m.movieName = :movieName")
	List<Movie> findByMovieName(String movieName);
	
	@Query("SELECT m FROM Movie m where m.genre = :genre")
	List<Movie> findByGenre(String genre);

	@Query("SELECT m FROM Movie m where m.language = :language")
	List<Movie> findByLanguage(String language);
}
