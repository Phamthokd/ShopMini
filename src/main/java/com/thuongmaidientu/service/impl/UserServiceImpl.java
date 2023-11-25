package com.thuongmaidientu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thuongmaidientu.model.User;
import com.thuongmaidientu.repository.UserRepository;
import com.thuongmaidientu.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByUserName(String userName) {
		
		return userRepository.findByUserName(userName);
	}

	@Override
	public User findById(Long id) {
		
		return userRepository.findById(id).get();
	}

	@Override
	public boolean delete(Long id) {
		try {
			this.userRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<User> findByRole(String role) {
		// TODO Auto-generated method stub
		return userRepository.findByRole(role);
	}

}
