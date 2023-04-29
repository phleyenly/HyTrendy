package com.lina.HyTrendy.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.lina.HyTrendy.reponsitory.PersonReponsitory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonService {
	private PersonReponsitory personReponsitory;
	private final ModelMapper mapper;
	
	public List<String> getRole () {
		return personReponsitory.getRole();
	}

}
