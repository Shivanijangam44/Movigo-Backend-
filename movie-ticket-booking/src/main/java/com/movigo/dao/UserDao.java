package com.movigo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movigo.entity.User;

public interface UserDao extends JpaRepository<User, Long>{
	
	

}
