package com.vaibhavvasurkar.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaibhavvasurkar.blog.entities.Category;
import com.vaibhavvasurkar.blog.exceptions.ResourceNotFoundException;
import com.vaibhavvasurkar.blog.payloads.CategoryDTO;
import com.vaibhavvasurkar.blog.repositories.CategoryRepo;
import com.vaibhavvasurkar.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		Category category = modelMapper.map(categoryDTO, Category.class);
		Category addedCategory = categoryRepo.save(category);
		return modelMapper.map(addedCategory, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
		Category categoryEntity = categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		Category category = modelMapper.map(categoryDTO, Category.class);
		
		Category updatedCategory = categoryRepo.save(category);
		return modelMapper.map(updatedCategory, CategoryDTO.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category categoryEntity = categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		categoryRepo.delete(categoryEntity);
	}

	@Override
	public CategoryDTO getCategory(Integer categoryId) {
		Category categoryEntity = categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		return modelMapper.map(categoryEntity, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getCategories() {
		List<Category> categories = categoryRepo.findAll();
		List<CategoryDTO> categoryDTOs=categories.stream().map((category)-> modelMapper.map(category, CategoryDTO.class))
											.collect(Collectors.toList());
		return categoryDTOs;
	}

}
