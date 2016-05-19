package com.spice.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spice.dao.repository.entity.BankEntity;

public interface BankRepository extends JpaRepository<BankEntity, Long> {
}
