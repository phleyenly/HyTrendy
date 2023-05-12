package com.lina.HyTrendy.api;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lina.HyTrendy.dto.PersonDto;
import com.lina.HyTrendy.service.PersonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class Person {
	private final PersonService personService;
	
	@GetMapping("/role")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<String> getRole () {
		return personService.getRole();
	}

	@PostMapping("/person")
	public PersonDto savePerson (@RequestBody PersonDto person) {
		return personService.savePerson(person);
	}
}
