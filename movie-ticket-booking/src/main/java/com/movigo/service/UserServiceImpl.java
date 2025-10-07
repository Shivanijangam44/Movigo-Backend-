package com.movigo.service;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movigo.dao.UserDao;
import com.movigo.dto.movieDtos.UserDto;
import com.movigo.entity.User;
import com.movigo.enums.Role;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ModelMapper modelmapper;
	
	
	@Override
	public UserDto registerUser(UserDto userdto) {
		User user = modelmapper.map(userdto, User.class);
		user.setRole(Role.ROLE_CUSTOMER);
		User savedUser = userDao.save(user);
		
		return modelmapper.map(savedUser, UserDto.class);
	}

}
