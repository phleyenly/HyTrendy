package com.lina.HyTrendy.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import com.lina.HyTrendy.projection.ProductOrderProjection;
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
	
//	public List<OrderDto> getOrderByUsername (String username)  {
//		List<OrderDto> orderDto = new ArrayList<>();
//		List<OrderEntity> orderEntity = orderReponsitory.getOrderByUsername(username);
//		for (OrderEntity order : orderEntity) {
//			orderDto.add(mapper.map(order, OrderDto.class));
//		}
//		return orderDto;
//	}
//	
	
	public List<OrderDto> findAllOrder ()  {
		List<OrderDto> orderDto = new ArrayList<>();
		List<OrderEntity> orderEntity = orderReponsitory.findAll();
		for (OrderEntity order : orderEntity) {
			orderDto.add(mapper.map(order, OrderDto.class));
		}
		return orderDto;
	}
	
//	public List<OrderDto> getAllOrder ()  {
//		List<OrderDto> orderEDto = new ArrayList<>();
//		List<OrderEntity> orderProjection = orderReponsitory.getAllOrder();
//		for (OrderEntity order : orderProjection) {
//			orderEDto.add(mapper.map(order, OrderDto.class));
//		}
//		return orderEDto;
//	}
	
	public List<OrderExtendDto> getAllOrder() {
		List<OrderExtendDto> order = new ArrayList<>();
		List<OrderEntity> orderEnity = orderReponsitory.getAllOrder();
		for (OrderEntity orderE : orderEnity) {
			order.add(mapper.map(orderE, OrderExtendDto.class));
			
		}
		
		for (OrderExtendDto o : order) {
			List<ProductOrderProjection> product = orderReponsitory.getProductByIdOrder(o.getId());
			o.setProducts(product);
		}
		
		return order;
	}
	
	//getOrderByUsername
	
	public List<OrderExtendDto> getOrderByUsername (String username) {
		List<OrderExtendDto> order = new ArrayList<>();
		List<OrderEntity> orderEnity = orderReponsitory.getOrderByUsername(username);
		for (OrderEntity orderE : orderEnity) {
			order.add(mapper.map(orderE, OrderExtendDto.class));
		}
		for (OrderExtendDto o : order) {
			List<ProductOrderProjection> product = orderReponsitory.getProductByIdOrder(o.getId());
			o.setProducts(product);
		}
		
		return order;
	}
	
	
	public Map<String, String> updateStatusOrderById (Long id,String status) {
		Map<String, String> result = new HashMap<>();
		List<String> statusOrder = new ArrayList<>();
		statusOrder.add("Chờ Xác Nhận");
		statusOrder.add("Chờ Lấy Hàng");
		statusOrder.add("Đang Giao Hàng");
		statusOrder.add("Đã Nhận Hàng");
		
		if(!statusOrder.contains(status)) {
			result.put("message", "Chỉnh Sửa Thất Bại");
			return result;
		} else {
			String resp = orderReponsitory.updateStatusOrderById(id, status);
		if(resp != null) {
			result.put("message", "Chỉnh Sửa Thành Công");
		} else {
			result.put("message", "Chỉnh Sửa Thất Bại");
		}
		return result;
		}
	}
	
	public Map<String, String> deleteOrderById(Long id) {
		Map<String, String> result = new HashMap<>();
		String resp = orderReponsitory.deleteOrderById(id);
		if(resp != null) {
			result.put("message", "Xóa Thành Công");
		} else {
			result.put("message", "Xóa Thất Bại");
		}
		return result;
	}
}
