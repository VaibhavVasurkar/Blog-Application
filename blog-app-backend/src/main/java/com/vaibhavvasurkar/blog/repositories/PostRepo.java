package com.vaibhavvasurkar.blog.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vaibhavvasurkar.blog.entities.Category;
import com.vaibhavvasurkar.blog.entities.Post;
import com.vaibhavvasurkar.blog.entities.UserEntity;

public interface PostRepo extends JpaRepository<Post, Integer>{
	
	Page<Post> findByUser(UserEntity user, Pageable pageable);
    Page<Post> findByCategory(Category category, Pageable pageable);
    
    //List<Post> findByTitleContaining(String title);
    
    @Query("select p from Post p where p.title like :key")
    List<Post> searchByTitle(@Param("key") String title);


}
