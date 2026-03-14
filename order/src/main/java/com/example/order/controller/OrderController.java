package com.example.order.controller;

import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.order.dto.OrderInfo;
import com.example.order.service.OrderService;
import com.example.order.vo.RequestOrder;
import com.example.order.vo.ResponseOrder;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/order-service")
@RequiredArgsConstructor
public class OrderController {

	private final Environment env;
	private final OrderService orderService;

	@GetMapping("/health-check")
	public String status() {
		return String.format(
			"It's Working in Order Service"
				+ ", port(local.server.port)=" + env.getProperty("local.server.port")
				+ ", port(server.port)=" + env.getProperty("server.port"));
	}

	@PostMapping("/{userId}/orders")
	public ResponseEntity<ResponseOrder> createOrder(@PathVariable("userId") String userId,
		@RequestBody RequestOrder request) {
		OrderInfo order = orderService.createOrder(request.toOrderDto(), userId);
		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseOrder.fromInfo(order));
	}

	@GetMapping("/{userId}/orders")
	public ResponseEntity<List<ResponseOrder>> getOrders(@PathVariable("userId") String userId) {
		List<OrderInfo> orders = orderService.getOrdersByUserId(userId);
		return ResponseEntity.status(HttpStatus.OK)
			.body(orders.stream().map(ResponseOrder::fromInfo).toList());
	}
}
