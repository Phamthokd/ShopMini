package com.thuongmaidientu.service;

import java.util.List;

import com.thuongmaidientu.model.User;

public interface UserService {
	User findByUserName(String userName);
	
	User findById(Long id);
	
	List<User> findByRole(String role);

	boolean delete(Long id);
}
