package com.example.catalog.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.catalog.jpa.CatalogEntity;
import com.example.catalog.jpa.CatalogRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

	private final CatalogRepository catalogRepository;

	@Override
	public void run(String... args) {
		catalogRepository.save(CatalogEntity.create("CATALOG-001", "berlin", 100, 1500));
		catalogRepository.save(CatalogEntity.create("CATALOG-002", "tokyo", 110, 2000));
		catalogRepository.save(CatalogEntity.create("CATALOG-003", "stockholm", 120, 2500));

		log.info("Catalog data initialized: {} items", catalogRepository.count());
	}
}
