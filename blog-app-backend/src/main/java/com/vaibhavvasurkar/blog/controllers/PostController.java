package com.vaibhavvasurkar.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaibhavvasurkar.blog.entities.Post;
import com.vaibhavvasurkar.blog.payloads.PostDTO;
import com.vaibhavvasurkar.blog.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDTO> createPost(@Valid
			@RequestBody PostDTO postDTO,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId
			)
	{
		PostDTO createdPostDTO = postService.createPost(postDTO, userId, categoryId);
		
		return new ResponseEntity<PostDTO>(createdPostDTO, HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable Integer userId){
		List<PostDTO> posts = postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable Integer categoryId){
		List<PostDTO> posts = postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<PostDTO>> getAllPosts(){
		List<PostDTO> posts = postService.getAllPost();
		return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> getPost(@PathVariable Integer postId){
		PostDTO post = postService.getPostById(postId);
		return new ResponseEntity<PostDTO>(post,HttpStatus.OK );
	}

}
