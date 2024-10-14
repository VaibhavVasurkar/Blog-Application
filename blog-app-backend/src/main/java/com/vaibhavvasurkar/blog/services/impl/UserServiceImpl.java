package com.vaibhavvasurkar.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaibhavvasurkar.blog.entities.User;
import com.vaibhavvasurkar.blog.exceptions.ResourceNotFoundException;
import com.vaibhavvasurkar.blog.payloads.UserDTO;
import com.vaibhavvasurkar.blog.repositories.UserRepo;
import com.vaibhavvasurkar.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		User user = this.dtoToUser(userDTO);
		User savedUser =  this.userRepo.save(user);
		return this.userToDTO(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, Integer userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id" , userId));
		
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setAbout(userDTO.getAbout());
		
		User updatedUser = this.userRepo.save(user);
		UserDTO updatedUserDTO = userToDTO(updatedUser);
		return updatedUserDTO;
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));			
				
		return this.userToDTO(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		
		List<User> users = userRepo.findAll();
		List<UserDTO> userDTOs = users.stream().map(user -> userToDTO(user))
				.collect(Collectors.toList());
		
		return userDTOs;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		
		userRepo.delete(user);
	}
	
	
	public User dtoToUser(UserDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setAbout(userDTO.getAbout());
		return user;
	}
	
	public UserDTO userToDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setEmail(user.getEmail());
		userDTO.setPassword(user.getPassword());
		userDTO.setAbout(user.getAbout());
		return userDTO;
	}

}
