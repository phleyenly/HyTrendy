package com.lina.HyTrendy.api;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lina.HyTrendy.dto.CartInfoDto;
import com.lina.HyTrendy.dto.CartItemDto;
import com.lina.HyTrendy.dto.PersonDto;
import com.lina.HyTrendy.projection.CartItemProjection;
import com.lina.HyTrendy.service.CartService;
import com.lina.HyTrendy.service.PersonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class Cart {
	private final CartService cartService;
	private final PersonService personService;
	
	@GetMapping("/cart")
	public List<CartItemDto> getCartByPersonId () {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		PersonDto person = personService.findByUsername(username);
		return cartService.getCartByPersonId(person.getId());
		
	}
	
	@PostMapping("/cart")
	public Map<String, String> createCartByUsername(@RequestBody CartInfoDto cart) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		return cartService.createCartByUsername(cart.getId(), username, cart.getQuantity(), cart.getSize());
	}
	
	@DeleteMapping("/cart/{id}")
	public Map<String, String> deleteCartByIdProduct (@PathVariable (name = "id", required = true) Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		PersonDto person = personService.findByUsername(username);
		return cartService.deleteCartByIdProduct(id, person.getId());
	}
	
}
