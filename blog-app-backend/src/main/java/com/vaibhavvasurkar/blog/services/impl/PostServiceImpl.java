package com.vaibhavvasurkar.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaibhavvasurkar.blog.entities.Category;
import com.vaibhavvasurkar.blog.entities.Post;
import com.vaibhavvasurkar.blog.entities.User;
import com.vaibhavvasurkar.blog.exceptions.ResourceNotFoundException;
import com.vaibhavvasurkar.blog.payloads.PostDTO;
import com.vaibhavvasurkar.blog.repositories.CategoryRepo;
import com.vaibhavvasurkar.blog.repositories.PostRepo;
import com.vaibhavvasurkar.blog.repositories.UserRepo;
import com.vaibhavvasurkar.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {
		
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User id", userId));
		
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		Post post = modelMapper.map(postDTO, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost = postRepo.save(post);
				
		return modelMapper.map(newPost, PostDTO.class);
	}

	@Override
	public PostDTO updatePost(PostDTO postDTO, Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PostDTO> getAllPost() {
		List<Post> posts = postRepo.findAll();
		List<PostDTO> postDTOs = posts.stream()
				.map((post)-> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return postDTOs;
	}

	@Override
	public PostDTO getPostById(Integer postId) {
		Post post = postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
		return modelMapper.map(post, PostDTO.class);
	}

	@Override
	public List<PostDTO> getPostsByCategory(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "Category id", categoryId));
		List<Post> posts = postRepo.findByCategory(category);
		List<PostDTO> postDTOs = posts.stream()
				.map((post)-> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		
		return postDTOs;
	}

	@Override
	public List<PostDTO> getPostsByUser(Integer userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
		List<Post> posts = postRepo.findByUser(user);
		List<PostDTO> postDTOs = posts.stream()
				.map((post)-> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return postDTOs;
	}

	@Override
	public List<PostDTO> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
