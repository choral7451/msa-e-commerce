package com.example.order.vo;

import com.example.order.dto.OrderDto;

import lombok.Getter;

@Getter
public class RequestOrder {

	private String productId;
	private Integer qty;
	private Integer unitPrice;

	public OrderDto toOrderDto() {
		return new OrderDto(productId, qty, unitPrice);
	}
}
