package com.thuongmaidientu.service;

import java.util.List;

import com.thuongmaidientu.model.Category;

public interface CategoryService {
	List<Category> getAll();
	Category create(Category category);
	Category findById(Long id);
	Category update(Category category);
	Boolean delete(Long id);
	Category findByName(String name);
}
