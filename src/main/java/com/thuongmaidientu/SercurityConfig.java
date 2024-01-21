package com.thuongmaidientu;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.thuongmaidientu.service.impl.CustomUserDetailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
				.requestMatchers("/shop/**").hasRole("SHOP")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/cart/**").authenticated()
				.requestMatchers("/order/**").authenticated()
				.requestMatchers("/add-cart/**").authenticated()
				.requestMatchers("/account/**").authenticated()
				.anyRequest().permitAll())
		.formLogin(login -> login.loginPage("/login").loginProcessingUrl("/login")
				.usernameParameter("username").passwordParameter("password")
				.successHandler(successHandler()) 
				.defaultSuccessUrl("/",true)
				)
		.logout(logout->logout.logoutUrl("/process-logout").logoutSuccessUrl("/login"));
		return http.build();
	}
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return (web)->web.debug(true).ignoring().requestMatchers("/statis/**","/assets/**","/vendor/**","/uploads/**");
	}
	
	@Bean
    public AuthenticationSuccessHandler successHandler() {
        return new SimpleUrlAuthenticationSuccessHandler() {
            @Override
            protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                String targetUrl = determineTargetUrl(request, response, authentication);
                if (response.isCommitted()) {
                    return;
                }
                clearAuthenticationAttributes(request);
                getRedirectStrategy().sendRedirect(request, response, targetUrl);
            }

            protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
                // Xác định URL mục tiêu dựa trên yêu cầu trước đó
                SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
                if (savedRequest != null) {
                    return savedRequest.getRedirectUrl();
                } else {
                   
                    return "/";
                }
            }
        };
    }

}
