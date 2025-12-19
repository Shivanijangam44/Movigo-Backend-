package com.movigo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.movigo.entity.Screen;

public interface ScreenDao extends JpaRepository<Screen, Long>{

	@Query("SELECT COUNT(s) from Screen s WHERE s.theater.theaterId = :theaterId")
	int countScreensByTheaterId( Long theaterId);
	
	
	List<Screen> findByTheaterTheaterId(Long theaterId);
}
