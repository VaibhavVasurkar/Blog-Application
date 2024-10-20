package com.vaibhavvasurkar.blog.services;

import java.util.List;

import com.vaibhavvasurkar.blog.payloads.RoleDTO;

public interface RoleService {

	RoleDTO addRole(RoleDTO dto);

	List<RoleDTO> getAllRoles();

}
