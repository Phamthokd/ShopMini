package com.thuongmaidientu.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.thuongmaidientu.model.CustomUserDetails;
import com.thuongmaidientu.model.User;
import com.thuongmaidientu.model.UserRole;
import com.thuongmaidientu.service.UserService;
@Service
public class CustomUserDetailService implements UserDetailsService {
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByUserName(username);
		if(user == null) {
			throw new UsernameNotFoundException("sai");
		}
		Collection<GrantedAuthority> grantedAuthoritiesSet = new HashSet<>();
		Set<UserRole> roles = user.getUserRoles();
		
		for (UserRole userRole : roles) {
			grantedAuthoritiesSet.add(new SimpleGrantedAuthority(userRole.getRole().getName()));
		}
		return new CustomUserDetails(user,grantedAuthoritiesSet);
	}

}
