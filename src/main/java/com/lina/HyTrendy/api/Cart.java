package com.lina.HyTrendy.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lina.HyTrendy.dto.CartItemDto;
import com.lina.HyTrendy.projection.CartItemProjection;
import com.lina.HyTrendy.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class Cart {
	private final CartService cartService;
	
	@GetMapping("/cart/{id}")
	public List<CartItemDto> getCartByPersonId (@PathVariable(name = "id", required = true) Long id) {
		return cartService.getCartByPersonId(id);
		
	}

}
