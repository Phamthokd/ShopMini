package com.thuongmaidientu.service;

import com.thuongmaidientu.model.User;

public interface UserService {
	User findByUserName(String userName);
	
	User findById(Long id);
}
