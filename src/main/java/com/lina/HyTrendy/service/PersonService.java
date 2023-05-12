package com.lina.HyTrendy.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lina.HyTrendy.dto.PersonDto;
import com.lina.HyTrendy.entity.PersonEntity;
import com.lina.HyTrendy.reponsitory.PersonReponsitory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonService {
	private final PersonReponsitory personReponsitory;
	private final ModelMapper mapper;
	private final PasswordEncoder passwordEncoder;
	
	public List<String> getRole () {
		return personReponsitory.getRole();
	}
	
	public PersonDto savePerson(PersonDto person) {
		PersonEntity personEntity = mapper.map(person, PersonEntity.class);
		personEntity.setPassword(passwordEncoder.encode(person.getPassword()));
		PersonEntity result = personReponsitory.save(personEntity);
		
		return mapper.map(result, PersonDto.class);
		
	}
	
	public PersonDto findByUsername(String username) {
		PersonDto person = new PersonDto();
		PersonEntity personEtt = personReponsitory.findByUsername(username);
		person = mapper.map(personEtt, PersonDto.class);
		return person;
	}

}
