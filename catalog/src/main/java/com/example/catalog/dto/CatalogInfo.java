package com.example.catalog.dto;

import java.time.LocalDateTime;

import com.example.catalog.jpa.CatalogEntity;

public record CatalogInfo(
	String productId,
	String productName,
	Integer stock,
	Integer unitPrice,
	LocalDateTime createdAt
) {

	public static CatalogInfo fromEntity(CatalogEntity entity) {
		return new CatalogInfo(
			entity.getProductId(),
			entity.getProductName(),
			entity.getStock(),
			entity.getUnitPrice(),
			entity.getCreatedAt()
		);
	}
}
