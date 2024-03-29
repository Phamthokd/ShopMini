package com.thuongmaidientu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.thuongmaidientu.model.User;
import com.thuongmaidientu.repository.UserRepository;
import com.thuongmaidientu.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private BCryptPasswordEncoder passwordEncoder;

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

	@Override
	public User update(User user) {
		
		return userRepository.save(user);
	}

	@Override
	public boolean checkCurrentPassword(User user, String currentPassword) {
		
		return passwordEncoder.matches(currentPassword, user.getPassword());
	}

	@Override
	public void updatePassword(User user, String newPassword) {
		
		String encodedPassword = passwordEncoder.encode(newPassword);

		user.setPassword(encodedPassword);
		
		userRepository.save(user);
	}

	@Override
	public long countByRole(String role) {
		// TODO Auto-generated method stub
		return userRepository.countByRole(role);
	}

	@Override
	public List<Object[]> findTop5SellersByOrderCount() {
		Pageable pageable = PageRequest.of(0, 2); 
	    return userRepository.findTop5SellersByOrderCount(pageable);
	}

	@Override
	public User create(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());		
		user.setPassword(encodedPassword);
		user.setRole("CUSTOMER");
		return userRepository.save(user);
	}

	@Override
	public List<Object[]> infoShop() {
		// TODO Auto-generated method stub
		return userRepository.infoShop();
	}

}
