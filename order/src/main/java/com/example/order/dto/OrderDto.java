package com.example.order.dto;

import com.example.order.jpa.OrderEntity;

public record OrderDto(
	String productId,
	Integer qty,
	Integer unitPrice
) {

	public OrderEntity toOrderEntity(String userId, String orderId) {
		return OrderEntity.create(productId, qty, unitPrice, userId, orderId);
	}
}
