package com.lina.HyTrendy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.data.repository.query.Param;
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
	
//	public PersonDto savePerson(PersonDto person) {
//		PersonEntity personEntity = mapper.map(person, PersonEntity.class);
//		personEntity.setPassword(passwordEncoder.encode(person.getPassword()));
//		PersonEntity result = personReponsitory.save(personEntity);
//		
//		return mapper.map(result, PersonDto.class);
//		
//	}
	
	public Map<String, String> savePerson(PersonDto person) {
		Map<String, String> result = new HashMap<>();
		PersonEntity personEntity = mapper.map(person, PersonEntity.class);
		personEntity.setPassword(passwordEncoder.encode(person.getPassword()));
		PersonEntity Personresult = personReponsitory.save(personEntity);
		if(Personresult.getId()== null) {
			result.put("message", "Thêm Thất Bại");
		} else {
			result.put("message", "Thêm Thành Công");
		}
		return result;
		
	}
	
	// hàm edit person 
	public Map<String, String> updateById(long id, String address, String name, String phone, 
			 String role, String username,  String password) {
		Map<String, String> result = new HashMap<>();
		String passwordEnding = passwordEncoder.encode(password);
		Long idReq = personReponsitory.updateById(id, address, name, phone, role, username, passwordEnding);
		if(idReq ==null) {
			result.put("message", "Chỉnh Sửa Thất Bại");
		} else {
			result.put("message", "Chỉnh Sửa Thành Công");
		}
		return result;
		
	}
	
	//hàm delete person
	
	public Map<String, String> deleteById(Long id) {
		Map<String, String> result = new HashMap<>();
		personReponsitory.deleteById(id);
		PersonEntity Person = personReponsitory.getPersonById(id);
		if(Person == null) {
			result.put("message", "Xóa Thành Công");
		} else {
			result.put("message", "Xóa Thất Bại");
		}
		return result;
	}
	
	public PersonDto findByUsername(String username) {
		PersonDto person = new PersonDto();
		PersonEntity personEtt = personReponsitory.findByUsername(username);
		person = mapper.map(personEtt, PersonDto.class);
		return person;
	}

	public  List<PersonDto> getAll() {
		List<PersonDto> person = new ArrayList<>();
		List<PersonEntity> personE = personReponsitory.getAll();
		for(PersonEntity p: personE) {
			person.add(mapper.map(p, PersonDto.class));
		}
		return person;
	}
	
	public  List<PersonDto> getByRole(String role) {
		List<PersonDto> person = new ArrayList<>();
		List<PersonEntity> personE = personReponsitory.getByRole(role);
		for(PersonEntity p: personE) {
			person.add(mapper.map(p, PersonDto.class));
		}
		return person;
	}
	
	public PersonDto getPersonById(long id) {
		PersonDto person = new PersonDto();
		PersonEntity personEtt = personReponsitory.getPersonById(id);
		person = mapper.map(personEtt, PersonDto.class);
		return person;
		
	}
	
	//hàm tạo cart
	
//	public Map<String, String> createCartByUsername(Long idProduct, String usernam, int quanity,String size) {
//		Map<String, String> result = new HashMap<>();
//		Long id = personReponsitory.createCartByUsername(idProduct, usernam, quanity, size);
//		if(id!= null) {
//			result.put("message", "Sản Phẩm Đã Được Thêm Vào Giỏ Hàng");
//		} else {
//			result.put("message", "Thêm vào giỏ hàng Thất Bại");
//		}
//		return result;
//	}
	
	
}
