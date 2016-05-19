package com.spice.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spice.dao.repository.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
