package com.example.catalog.jpa;

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
@Table(name = "catalogs")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CatalogEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String productId;

	@Column(nullable = false)
	private String productName;

	@Column(nullable = false)
	private Integer stock;

	@Column(nullable = false)
	private Integer unitPrice;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	private CatalogEntity(String productId, String productName, Integer stock, Integer unitPrice, LocalDateTime createdAt) {
		this.productId = productId;
		this.productName = productName;
		this.stock = stock;
		this.unitPrice = unitPrice;
		this.createdAt = createdAt;
	}

	public static CatalogEntity create(String productId, String productName, Integer stock, Integer unitPrice) {
		return new CatalogEntity(productId, productName, stock, unitPrice, LocalDateTime.now());
	}
}
