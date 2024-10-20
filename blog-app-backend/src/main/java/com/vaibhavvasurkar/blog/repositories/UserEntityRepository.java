package com.vaibhavvasurkar.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaibhavvasurkar.blog.entities.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, Integer>{
	
	Optional<UserEntity> findByEmail(String email);

}
