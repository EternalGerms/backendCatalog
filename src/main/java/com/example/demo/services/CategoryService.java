package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.entities.Category;
import com.example.demo.repositories.CategoryRepository;

@Service   
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		List<Category> list = repository.findAll();
		
		List<CategoryDTO> listDto = new ArrayList<>();
		for (Category cat : list) {
			listDto.add(new CategoryDTO(cat));
		}
		
		return listDto;
	}

}
 