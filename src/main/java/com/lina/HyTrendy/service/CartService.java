package com.lina.HyTrendy.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.lina.HyTrendy.dto.CartItemDto;
import com.lina.HyTrendy.projection.CartItemProjection;
import com.lina.HyTrendy.reponsitory.PersonReponsitory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
	private final PersonReponsitory cartReponsitory;
	private final ModelMapper mapper;
	
	public List<CartItemDto> getCartByPersonId (Long id) {
		List<CartItemDto> cartDto = new ArrayList<>();
		List<CartItemProjection> cartProjection = cartReponsitory.getCartByPersonById(id);
		for(CartItemProjection c:cartProjection) {
			cartDto.add(mapper.map(c, CartItemDto.class));
		}
		return cartDto;
	}

}
