package com.movigo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.movigo.entity.Show;

public interface ShowDao extends JpaRepository<Show, Long> {
	
	//@Query("SELECT s from Show s WHERE s.movie.movieName = :movieName")
	List<Show> findByMovieMovieName(String movieName);

}
