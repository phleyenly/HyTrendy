package com.lina.HyTrendy.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.neo4j.cypherdsl.core.Foreach;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.lina.HyTrendy.dto.OrderDto;
import com.lina.HyTrendy.dto.OrderExtendDto;
import com.lina.HyTrendy.entity.OrderEntity;
import com.lina.HyTrendy.projection.OrderProjection;
import com.lina.HyTrendy.reponsitory.OrderReponsitory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	private final OrderReponsitory orderReponsitory;
	private final ModelMapper mapper;
	
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
	
	public List<OrderDto> getOrderByUsername (String username)  {
		List<OrderDto> orderDto = new ArrayList<>();
		List<OrderEntity> orderEntity = orderReponsitory.getOrderByUsername(username);
		for (OrderEntity order : orderEntity) {
			orderDto.add(mapper.map(order, OrderDto.class));
		}
		return orderDto;
	}
	
	
	public List<OrderDto> findAllOrder ()  {
		List<OrderDto> orderDto = new ArrayList<>();
		List<OrderEntity> orderEntity = orderReponsitory.findAll();
		for (OrderEntity order : orderEntity) {
			orderDto.add(mapper.map(order, OrderDto.class));
		}
		return orderDto;
	}
	
	public List<OrderDto> getAllOrder ()  {
		List<OrderDto> orderEDto = new ArrayList<>();
		List<OrderEntity> orderProjection = orderReponsitory.getAllOrder();
		for (OrderEntity order : orderProjection) {
			orderEDto.add(mapper.map(order, OrderDto.class));
		}
		return orderEDto;
	}
}
