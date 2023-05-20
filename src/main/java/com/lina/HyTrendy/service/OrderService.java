package com.lina.HyTrendy.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.lina.HyTrendy.reponsitory.OrderReponsitory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	private final OrderReponsitory orderReponsitory;
	
	public Map<String, String> createOrder(Long idPerson,String status, LocalDate date) {
		Map<String, String> result = new HashMap<>();
		Long idCart = orderReponsitory.createOrder(idPerson, status, date);
		if(idCart != null) {
			result.put("message", "Đặt Hàng Thành Công");
		} else {
			result.put("message", "Đặt Hàng Thất Bại");
		}
		return result;
		
		
	}
	

}
