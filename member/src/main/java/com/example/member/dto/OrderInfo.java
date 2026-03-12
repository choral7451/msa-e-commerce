package com.example.member.dto;

import java.time.LocalDateTime;

public record OrderInfo(
	String productId,
	String orderId,
	Integer quantity,
	Integer unitPrice,
	Integer totalPrice,
	LocalDateTime createdAt
) {
}
