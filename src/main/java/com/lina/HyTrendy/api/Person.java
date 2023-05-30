package com.lina.HyTrendy.api;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public Map<String, String> savePerson (@RequestBody PersonDto person) {
		return personService.savePerson(person);
	}
	
//	@GetMapping("/person")
//	public List<PersonDto> getAll() {
//		return personService.getAll();
//	}
	
//	@GetMapping("/person/{role}")
//	public List<PersonDto> getByRole(@PathVariable(name = "role", required = false) String role) {
//		return personService.getByRole(role);
//	}
	
	@GetMapping("/person")
	public List<PersonDto> getPerson(@RequestParam(name = "role", required = false) String role) {
		if(role == null) {
			return personService.getAll();
		} else {
			return personService.getByRole(role);
		}
	}
	
	@GetMapping("/person/{id}")
	public PersonDto getPersonById(@PathVariable(name = "id", required = false) Long id) {
		return personService.getPersonById(id);
	}
	
	@PutMapping("/person/{id}")
	public Map<String, String> editPersonById(@PathVariable(name = "id", required = true) Long id, @RequestBody PersonDto person ) {
		return personService.updateById(id, person.getAddress(), person.getName(), person.getPhone(), person.getRole(), person.getUsername(), person.getPassword());
	}
	
	@DeleteMapping("/person/{id}")
	public Map<String, String> deleteById(@PathVariable(name = "id", required = true) Long id) {
		return personService.deleteById(id);
	}
	
	@GetMapping("person/user")
	public PersonDto findPersonByUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		return personService.findByUsername(username);
	}
}
