package com.lina.HyTrendy.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lina.HyTrendy.service.PersonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class Person {
	private final PersonService personService;
	
	@GetMapping("/person")
	public List<String> getRole () {
		return personService.getRole();
	}

}
