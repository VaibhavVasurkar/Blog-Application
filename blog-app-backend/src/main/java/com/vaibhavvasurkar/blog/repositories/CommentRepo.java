package com.vaibhavvasurkar.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaibhavvasurkar.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
