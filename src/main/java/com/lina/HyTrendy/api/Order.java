package com.lina.HyTrendy.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lina.HyTrendy.dto.OrderDto;
import com.lina.HyTrendy.dto.PersonDto;
import com.lina.HyTrendy.service.OrderService;
import com.lina.HyTrendy.service.PersonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class Order {
	private final OrderService orderService;
	private final PersonService personService;
	
	@PostMapping("/order") 
	public Map<String, String> createOrder() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		PersonDto person = personService.findByUsername(username);
		LocalDate date = LocalDate.now();
		String status = "Chờ Xác Nhận";
		return orderService.createOrder(person.getId(), status, date);
	}
	
	@GetMapping("username/order")
	public List<OrderDto> getOrderByUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		return orderService.getOrderByUsername(username);
	}
	
	@GetMapping("order")
	public List<OrderDto> findAllOrder() {
		return orderService.findAllOrder();
	}

}
