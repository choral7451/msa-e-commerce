package com.example.order.vo;

import java.time.LocalDateTime;

import com.example.order.dto.OrderInfo;

public record ResponseOrder(
	String productId,
	Integer qty,
	Integer unitPrice,
	Integer totalPrice,
	LocalDateTime createdAt,
	String orderId
) {

	public static ResponseOrder fromInfo(OrderInfo info) {
		return new ResponseOrder(
			info.productId(),
			info.qty(),
			info.unitPrice(),
			info.totalPrice(),
			info.createdAt(),
			info.orderId()
		);
	}
}
