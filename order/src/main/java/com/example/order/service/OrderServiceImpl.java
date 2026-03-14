package com.example.order.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.order.dto.OrderDto;
import com.example.order.dto.OrderInfo;
import com.example.order.jpa.OrderEntity;
import com.example.order.jpa.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	@Override
	public OrderInfo createOrder(OrderDto orderDto, String userId) {
		String orderId = UUID.randomUUID().toString();
		OrderEntity order = orderRepository.save(orderDto.toOrderEntity(userId, orderId));
		return OrderInfo.fromEntity(order);
	}

	@Override
	public List<OrderInfo> getOrdersByUserId(String userId) {
		return orderRepository.findByUserId(userId).stream()
			.map(OrderInfo::fromEntity)
			.toList();
	}
}
