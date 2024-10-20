package com.vaibhavvasurkar.blog.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaibhavvasurkar.blog.entities.Role;
import com.vaibhavvasurkar.blog.payloads.RoleDTO;
import com.vaibhavvasurkar.blog.repositories.RoleRepo;
import com.vaibhavvasurkar.blog.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public RoleDTO addRole(RoleDTO dto) {
		Role role = new Role();
		role.setName(dto.getName());
		Role savedRole = roleRepo.save(role);
		
		return modelMapper.map(savedRole, RoleDTO.class);
	}

	@Override
	public List<RoleDTO> getAllRoles() {
		// TODO Auto-generated method stub
		return null;
	}

}
