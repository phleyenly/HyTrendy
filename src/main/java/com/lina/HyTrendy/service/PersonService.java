package com.lina.HyTrendy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
	private final UserDetailsServiceImpl userDetailsServiceImpl;
	
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
		boolean check = personReponsitory.existsByUsername(person.getUsername());
		if(check) {
			result.put("message", "Username Đã Tồn Tại ");
			return result;
		}
		
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
	
	public Map<String, String> checkPasswordByUrsernane( String password) {
		Map<String, String> result = new HashMap<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		UserDetails user =   userDetailsServiceImpl.loadUserByUsername(username);
		if(passwordEncoder.matches(password, user.getPassword())) {
			result.put("message", "OK");
		} else {
			result.put("message", "Mật khẩu không đúng");
		}
		return result;
		
	}
	
	public Map<String, String> setPasswordByUsername(String username, String oldPass, String newPass, String confirmPass) {
		Map<String, String> result = new HashMap<>();
		Map<String, String> checkOldPassword = checkPasswordByUrsernane(oldPass);
		if ("OK".equals(checkOldPassword.get("message")) == false ) {
			result.put("message", "Mật khẩu cũ không đúng");
		} else if (newPass.equals(confirmPass) ) {
			String passwordChange = passwordEncoder.encode(newPass);
			String reponse = this.personReponsitory.setPasswordByUsername(username, passwordChange);
			if (reponse.equals("OK")) {
				result.put("message", "Thay đổi Mật Khẩu Thành Công");
			} else {
				result.put("message", "Thay đổi Mật Khẩu Thất Bại");
			}
		} else {
			result.put("message", "Mật khẩu mới và confirm mật khẩu không khớp");
		}
		return result;
	}
	
	
}
