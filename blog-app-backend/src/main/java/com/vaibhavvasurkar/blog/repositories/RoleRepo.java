package com.vaibhavvasurkar.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaibhavvasurkar.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {

	Role findByName(String roleName);
}
