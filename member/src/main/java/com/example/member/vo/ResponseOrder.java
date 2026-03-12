package com.example.member.vo;

import java.time.LocalDateTime;

import com.example.member.dto.MemberInfo;

public record ResponseOrder(
	String productId,
	String orderId,
	Integer quantity,
	Integer unitPrice,
	Integer totalPrice,
	LocalDateTime createdAt
) {
}


