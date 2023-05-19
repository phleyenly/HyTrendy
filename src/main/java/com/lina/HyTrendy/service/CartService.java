package com.lina.HyTrendy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public Map<String, String> createCartByUsername(Long idProduct, String username, int quanity,String size) {
		Map<String, String> result = new HashMap<>();
		Long id = cartReponsitory.createCartByUsername(idProduct, username, quanity, size);
		if(id!= null) {
			result.put("message", "Sản Phẩm Đã Được Thêm Vào Giỏ Hàng");
		} else {
			result.put("message", "Thêm vào giỏ hàng Thất Bại");
		}
		return result;
	}
	
	public Map<String, String> setQuantityCart(Long idCart, int quantity) {
		Map<String, String> result = new HashMap<>();
		Long id = cartReponsitory.setQuantityCart(idCart, quantity);
		if(id!= null) {
			result.put("message", "Sản Phẩm Đã Được Thêm Vào Giỏ Hàng");
		} else {
			result.put("message", "Thêm vào giỏ hàng Thất Bại");
		}
		return result;
	}
	
	public Map<String, String> deleteCartByIdCart(Long idCart, Long idPerson) {
		Map<String, String> result = new HashMap<>();
		String response = cartReponsitory.deleteCartByIdCart(idCart, idPerson);
		if(response != null) {
			result.put("message", "Sản Phẩm Đã Được Xóa Khỏi Giỏ Hàng");
		} else {
			result.put("message", "Xóa Thất Bại");
		}
		return result;
	}

}
