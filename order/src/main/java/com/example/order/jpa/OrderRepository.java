package com.example.order.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
	List<OrderEntity> findByUserId(String userId);
}
