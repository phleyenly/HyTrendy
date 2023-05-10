package com.lina.HyTrendy.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lina.HyTrendy.config.JwtUtil;
import com.lina.HyTrendy.dto.LoginDto;
import com.lina.HyTrendy.service.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class AuthenticationApi {
	private final JwtUtil jwtUtil;
	private final UserDetailsServiceImpl userDetailsServiceImpl;
	private final PasswordEncoder passwordEncoder;
	
	@PostMapping("/login")
	public String login (@RequestBody LoginDto auth) {
		UserDetails user =   userDetailsServiceImpl.loadUserByUsername(auth.getUsername());
		 
		 if(user != null && passwordEncoder.matches(auth.getPassword(), user.getPassword())) {
			 return jwtUtil.generateToken(user.getUsername());
		 }
		 return "Username or password is not correct";
	}

}
