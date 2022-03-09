package com.townhall.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.townhall.api.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}