package com.vaibhavvasurkar.blog.payloads;

import java.util.Date;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDTO {
	
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
}
