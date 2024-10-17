package com.vaibhavvasurkar.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaibhavvasurkar.blog.entities.Comment;
import com.vaibhavvasurkar.blog.entities.Post;
import com.vaibhavvasurkar.blog.exceptions.ResourceNotFoundException;
import com.vaibhavvasurkar.blog.payloads.CommentDTO;
import com.vaibhavvasurkar.blog.repositories.CommentRepo;
import com.vaibhavvasurkar.blog.repositories.PostRepo;
import com.vaibhavvasurkar.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {
		
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
		Comment comment = modelMapper.map(commentDTO, Comment.class);
		comment.setPost(post);
		Comment savedComment = commentRepo.save(comment);
		return modelMapper.map(savedComment, CommentDTO.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		Comment comment = commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "comment id", commentId));
		commentRepo.delete(comment);
	}

}
