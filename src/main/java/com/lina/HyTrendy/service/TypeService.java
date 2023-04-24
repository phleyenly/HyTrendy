package com.lina.HyTrendy.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.lina.HyTrendy.reponsitory.TypeReponsitory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TypeService {
	private final ModelMapper mapper;
	private final TypeReponsitory typeReponsitory;
	
	public String getTypeByCode (String categoryCode, String typeCode)  {
		return typeReponsitory.getTypeByCode(categoryCode, typeCode);
		
	}

}
