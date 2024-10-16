package com.vaibhavvasurkar.blog.services;

import java.util.List;

import com.vaibhavvasurkar.blog.entities.Post;
import com.vaibhavvasurkar.blog.payloads.PostDTO;
import com.vaibhavvasurkar.blog.payloads.PostResponse;

public interface PostService {
	
	PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);
	
	PostDTO updatePost(PostDTO postDTO, Integer postId);
	
	void deletePost(Integer postId);
	
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy,String sortDir);
	
	PostDTO getPostById(Integer postId);
	
	PostResponse getPostsByCategory(Integer pageNumber, Integer pageSize, Integer categoryId);
	
	PostResponse getPostsByUser(Integer pageNumber, Integer pageSize, Integer userId);
	
	List<PostDTO> searchPosts(String keyword);
	
	
	
	
}
