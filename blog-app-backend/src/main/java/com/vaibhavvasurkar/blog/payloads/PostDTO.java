package com.vaibhavvasurkar.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vaibhavvasurkar.blog.entities.Comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDTO {
	
	private Integer postId;
	
	@NotBlank
	@Size(min=4, message="Min size of post title is 4")
	private String title;
	
	@NotBlank
	@Size(min=10, message="min size of content is 10")
	private String content;
	
	private String imageName;
	

	private Date addedDate;
	
	
	private CategoryDTO category;
	
	
	private UserDTO user;
	
	private Set<CommentDTO> comments = new HashSet<>();
}
