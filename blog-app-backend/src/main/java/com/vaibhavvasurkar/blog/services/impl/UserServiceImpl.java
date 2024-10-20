package com.vaibhavvasurkar.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vaibhavvasurkar.blog.entities.Role;
import com.vaibhavvasurkar.blog.entities.UserEntity;
import com.vaibhavvasurkar.blog.exceptions.ResourceNotFoundException;
import com.vaibhavvasurkar.blog.payloads.UserDTO;
import com.vaibhavvasurkar.blog.repositories.RoleRepo;
import com.vaibhavvasurkar.blog.repositories.UserEntityRepository;
import com.vaibhavvasurkar.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserEntityRepository userRepo;

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		UserEntity user = this.modelMapper.map(userDTO, UserEntity.class);

		String password = userDTO.getPassword();
		String encodedPassword = passwordEncoder.encode(password);
		user.setPassword(encodedPassword);
		
		Role role = roleRepo.findByName("ROLE_ADMIN");
		user.setRole(role);

		UserEntity savedUser = this.userRepo.save(user);
		return this.userToDTO(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, Integer userId) {
		UserEntity user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());

		String password = userDTO.getPassword();
		String encodedPassword = passwordEncoder.encode(password);
		user.setPassword(encodedPassword);

		user.setAbout(userDTO.getAbout());

		Role role = roleRepo.findByName("ROLE_NORMALUSER");
		user.setRole(role);
		
		UserEntity updatedUser = this.userRepo.save(user);
		UserDTO updatedUserDTO = userToDTO(updatedUser);
		return updatedUserDTO;
	}

	@Override
	public UserDTO getUserById(Integer userId) {

		UserEntity user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		return this.userToDTO(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {

		List<UserEntity> users = userRepo.findAll();
		List<UserDTO> userDTOs = users.stream().map(user -> userToDTO(user)).collect(Collectors.toList());

		return userDTOs;
	}

	@Override
	public void deleteUser(Integer userId) {

		UserEntity user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		userRepo.delete(user);
	}

	public UserEntity dtoToUser(UserDTO userDTO) {
		UserEntity user = this.modelMapper.map(userDTO, UserEntity.class);
//		user.setId(userDTO.getId());
//		user.setName(userDTO.getName());
//		user.setEmail(userDTO.getEmail());
//		user.setPassword(userDTO.getPassword());
//		user.setAbout(userDTO.getAbout());
		return user;
	}

	public UserDTO userToDTO(UserEntity user) {
		UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
		return userDTO;
	}

	@Override
	public UserDTO registerNewUser(UserDTO userDTO) {
		
		UserEntity user = modelMapper.map(userDTO, UserEntity.class);
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		Role role = roleRepo.findByName("ROLE_NORMALUSER");
		user.setRole(role);
		
		UserEntity newUser = userRepo.save(user);
		return modelMapper.map(newUser, UserDTO.class);
	}

}
