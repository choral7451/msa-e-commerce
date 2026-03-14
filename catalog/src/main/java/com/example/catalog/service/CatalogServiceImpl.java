package com.example.catalog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.catalog.dto.CatalogInfo;
import com.example.catalog.jpa.CatalogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

	private final CatalogRepository catalogRepository;

	@Override
	public List<CatalogInfo> getAllCatalogs() {
		return catalogRepository.findAll().stream()
			.map(CatalogInfo::fromEntity)
			.toList();
	}
}
