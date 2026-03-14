package com.example.order.dto;

import java.time.LocalDateTime;

import com.example.order.jpa.OrderEntity;

public record OrderInfo(
	String productId,
	Integer qty,
	Integer unitPrice,
	Integer totalPrice,
	LocalDateTime createdAt,
	String orderId
) {

	public static OrderInfo fromEntity(OrderEntity entity) {
		return new OrderInfo(
			entity.getProductId(),
			entity.getQty(),
			entity.getUnitPrice(),
			entity.getTotalPrice(),
			entity.getCreatedAt(),
			entity.getOrderId()
		);
	}
}
