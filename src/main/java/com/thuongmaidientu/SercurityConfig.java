package com.thuongmaidientu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.thuongmaidientu.service.impl.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SercurityConfig {
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {		
		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((auth)->auth
//				.requestMatchers("/*").permitAll()				
				.requestMatchers("/shop/**").hasAuthority("SHOP")
				.requestMatchers("/admin/**").hasAuthority("ADMIN")
				.anyRequest().permitAll())
		.formLogin(login -> login.loginPage("/login").loginProcessingUrl("/login")
				.usernameParameter("username").passwordParameter("password")	
				.defaultSuccessUrl("/",true))
		.logout(logout->logout.logoutUrl("/process-logout").logoutSuccessUrl("/login"));
		return http.build();
	}
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return (web)->web.debug(true).ignoring().requestMatchers("/statis/**","/assets/**","/vendor/**","/uploads/**");
	}

}
