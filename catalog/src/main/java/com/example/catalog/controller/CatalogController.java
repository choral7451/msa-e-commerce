package com.example.catalog.controller;

import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.catalog.dto.CatalogInfo;
import com.example.catalog.service.CatalogService;
import com.example.catalog.vo.ResponseCatalog;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/catalog-service")
@RequiredArgsConstructor
public class CatalogController {

	private final Environment env;
	private final CatalogService catalogService;

	@GetMapping("/health-check")
	public String status() {
		return String.format(
			"It's Working in Catalog Service"
				+ ", port(local.server.port)=" + env.getProperty("local.server.port")
				+ ", port(server.port)=" + env.getProperty("server.port"));
	}

	@GetMapping("/catalogs")
	public ResponseEntity<List<ResponseCatalog>> getCatalogs() {
		List<CatalogInfo> catalogs = catalogService.getAllCatalogs();
		return ResponseEntity.status(HttpStatus.OK)
			.body(catalogs.stream().map(ResponseCatalog::fromInfo).toList());
	}
}
