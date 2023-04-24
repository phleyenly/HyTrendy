package com.lina.HyTrendy.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lina.HyTrendy.service.TypeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class Type {
	private final TypeService typeService;
	
	@GetMapping("/type/{categoryCode}/{typeCode}")
	public ResponseEntity<String> getTypeByCode (@PathVariable(name = "categoryCode", required = false) String categoryCode,
			@PathVariable(name = "typeCode" , required = false) String typeCode) {
		String json = typeService.getTypeByCode(categoryCode, typeCode);
        return new ResponseEntity<>(json,HttpStatus.OK);
	}

}
