package com.thuongmaidientu.service;

import java.util.List;

import com.thuongmaidientu.model.User;

public interface UserService {
	User findByUserName(String userName);
	
	User update(User user);
	
	User findById(Long id);
	
	List<User> findByRole(String role);

	boolean delete(Long id);
	
	boolean checkCurrentPassword(User user, String currentPassword);
	
	void updatePassword(User user, String newPassword);
	
	long countByRole(String role);
	
	List<Object[]> findTop5SellersByOrderCount() ;
	
	User create(User user);
	 
}
