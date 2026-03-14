package com.example.order.service;

import java.util.List;

import com.example.order.dto.OrderDto;
import com.example.order.dto.OrderInfo;

public interface OrderService {
	OrderInfo createOrder(OrderDto orderDto, String userId);
	List<OrderInfo> getOrdersByUserId(String userId);
}
