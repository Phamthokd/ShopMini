package com.thuongmaidientu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thuongmaidientu.model.User;
import com.thuongmaidientu.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByUserName(String userName) {
		
		return userRepository.findByUserName(userName);
	}

}
