package com.example.catalog.vo;

import java.time.LocalDateTime;

import com.example.catalog.dto.CatalogInfo;

public record ResponseCatalog(
	String productId,
	String productName,
	Integer unitPrice,
	Integer stock,
	LocalDateTime createdAt
) {

	public static ResponseCatalog fromInfo(CatalogInfo info) {
		return new ResponseCatalog(
			info.productId(),
			info.productName(),
			info.unitPrice(),
			info.stock(),
			info.createdAt()
		);
	}
}
