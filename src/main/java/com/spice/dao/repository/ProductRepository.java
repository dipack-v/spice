package com.spice.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spice.dao.repository.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
