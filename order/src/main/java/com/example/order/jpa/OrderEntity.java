package com.example.order.jpa;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String productId;

	@Column(nullable = false)
	private Integer qty;

	@Column(nullable = false)
	private Integer unitPrice;

	@Column(nullable = false)
	private Integer totalPrice;

	@Column(nullable = false)
	private String userId;

	@Column(nullable = false, unique = true)
	private String orderId;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	private OrderEntity(String productId, Integer qty, Integer unitPrice, Integer totalPrice, String userId, String orderId, LocalDateTime createdAt) {
		this.productId = productId;
		this.qty = qty;
		this.unitPrice = unitPrice;
		this.totalPrice = totalPrice;
		this.userId = userId;
		this.orderId = orderId;
		this.createdAt = createdAt;
	}

	public static OrderEntity create(String productId, Integer qty, Integer unitPrice, String userId, String orderId) {
		return new OrderEntity(productId, qty, unitPrice, qty * unitPrice, userId, orderId, LocalDateTime.now());
	}
}
