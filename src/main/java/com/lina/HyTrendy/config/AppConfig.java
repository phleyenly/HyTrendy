package com.lina.HyTrendy.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class AppConfig {
	
	@Bean
	public JwtFilter jwtFilter() {
		return new JwtFilter();
	}
	
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		
		http.authorizeHttpRequests()
			.requestMatchers("/api/person").permitAll()
			.requestMatchers("/api/login").permitAll()
			.requestMatchers("/api/category").permitAll()
			.requestMatchers("/api/product").permitAll()
			.anyRequest().authenticated();
		
//		http.formLogin(); sài form login của server
//		
		http.exceptionHandling().authenticationEntryPoint((req, res, ex) -> {
			res.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
		});
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
		
		
		return http.build();
		
		
	}
}
