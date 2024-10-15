package com.vaibhavvasurkar.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaibhavvasurkar.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
