package com.spice.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spice.dao.repository.entity.ContactEntity;

public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
}
