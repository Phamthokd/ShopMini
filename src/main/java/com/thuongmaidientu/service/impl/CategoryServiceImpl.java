package com.thuongmaidientu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thuongmaidientu.model.Category;
import com.thuongmaidientu.repository.CategoryRepository;
import com.thuongmaidientu.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> getAll() {
		// TODO Auto-generated method stub
		return this.categoryRepository.findAll();
	}

	@Override
	public Category create(Category category) {
		// TODO Auto-generated method stub

		return categoryRepository.save(category);
	}

	@Override
	public Category findById(Long id) {
		return this.categoryRepository.findById(id).get();
	}

	@Override
	public Category update(Category category) {	
		return	this.categoryRepository.save(category);
	}

	@Override
	public Boolean delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category findByName(String name) {
		return this.categoryRepository.findByName(name);
	}

	@Override
	public List<Category> searchCategories(String keyword) {
		// TODO Auto-generated method stub
		return categoryRepository.searchCategories(keyword);
	}

	@Override
	public List<Category> findFirst7ByOrderByIdAsc() {
		// TODO Auto-generated method stub
		return categoryRepository.findFirst7ByOrderByIdAsc();
	}

}
