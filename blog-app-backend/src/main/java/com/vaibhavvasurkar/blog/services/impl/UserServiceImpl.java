package com.vaibhavvasurkar.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		User user = this.dtoToUser(userDTO);
		User savedUser = this.userRepo.save(user);
		return this.userToDTO(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

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

		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		return this.userToDTO(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {

		List<User> users = userRepo.findAll();
		List<UserDTO> userDTOs = users.stream().map(user -> userToDTO(user)).collect(Collectors.toList());

		return userDTOs;
	}

	@Override
	public void deleteUser(Integer userId) {

		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		userRepo.delete(user);
	}

	public User dtoToUser(UserDTO userDTO) {
		User user = this.modelMapper.map(userDTO, User.class);
//		user.setId(userDTO.getId());
//		user.setName(userDTO.getName());
//		user.setEmail(userDTO.getEmail());
//		user.setPassword(userDTO.getPassword());
//		user.setAbout(userDTO.getAbout());
		return user;
	}

	public UserDTO userToDTO(User user) {
		UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
		return userDTO;
	}

}
