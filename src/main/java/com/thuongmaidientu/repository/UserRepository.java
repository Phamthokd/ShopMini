package com.thuongmaidientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thuongmaidientu.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserName(String userName);
	
	
}
