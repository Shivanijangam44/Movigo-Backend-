package com.movigo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.movigo.entity.Theater;

public interface TheaterDao extends JpaRepository<Theater, Long> {
	
	@Query("SELECT t from Theater t WHERE t.theaterName = :theaterName")
	List<Theater> findByTheaterName(String theaterName);
	
	@Query("SELECT t from Theater t WHERE t.location = :location")
	List<Theater> findByLocation(String location);
}
