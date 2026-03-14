package com.example.catalog.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<CatalogEntity, Long> {
}
