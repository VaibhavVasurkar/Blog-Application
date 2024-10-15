package com.vaibhavvasurkar.blog.services;

import java.util.List;

import com.vaibhavvasurkar.blog.entities.Post;
import com.vaibhavvasurkar.blog.payloads.PostDTO;

public interface PostService {
	
	PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);
	
	PostDTO updatePost(PostDTO postDTO, Integer postId);
	
	void deletePost(Integer postId);
	
	List<PostDTO> getAllPost();
	
	PostDTO getPostById(Integer postId);
	
	List<PostDTO> getPostsByCategory(Integer categoryId);
	
	List<PostDTO> getPostsByUser(Integer userId);
	
	List<PostDTO> searchPosts(String keyword);
	
	
	
	
}
