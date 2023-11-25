package com.thuongmaidientu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thuongmaidientu.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserName(String userName);
	
//	@Query("SELECT u FROM User u WHERE u.id IN (SELECT ur.user.id FROM UserRole ur WHERE ur.role.name = :roleName)")
//	List<User> findUsersByRoleShop(@Param("roleName") String roleName);
	
	 //@Query("SELECT u FROM User u WHERE u.id IN (SELECT ur.userId FROM UserRole ur WHERE ur.roleId IN (SELECT r.id FROM Role r WHERE r.name = %?1%))")
	
	List<User> findByRole(String role);
	
}
