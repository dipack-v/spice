package com.spice.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spice.dao.repository.entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}
