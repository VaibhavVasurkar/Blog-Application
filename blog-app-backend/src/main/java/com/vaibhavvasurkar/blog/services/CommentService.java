package com.vaibhavvasurkar.blog.services;

import com.vaibhavvasurkar.blog.payloads.CommentDTO;

public interface CommentService {
	
	CommentDTO createComment(CommentDTO commentDTO, Integer postId);
	
	void deleteComment(Integer commentId);

}
