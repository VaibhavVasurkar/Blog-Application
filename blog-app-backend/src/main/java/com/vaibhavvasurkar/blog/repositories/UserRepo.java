package com.vaibhavvasurkar.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaibhavvasurkar.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
