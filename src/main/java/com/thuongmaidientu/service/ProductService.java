package com.thuongmaidientu.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.thuongmaidientu.model.Category;
import com.thuongmaidientu.model.Product;
import com.thuongmaidientu.model.User;

public interface ProductService {
	List<Product> getAll();
	Product create(Product product);
	Product update(Product product);
	Boolean delete(Long id);
	Product findById(Long id);
	List<Product> findByUserProduct(User userProduct);
	void processFile(
			MultipartFile file1,
			MultipartFile file2, 
			MultipartFile file3, 
			MultipartFile file4,
			String description,
			String shortDescription,
			Product product);
	void processCategory(Category category,String categoryInput,Product product);
	
	void processFileEdit(MultipartFile file1,
			MultipartFile file2, 
			MultipartFile file3, 
			MultipartFile file4,
			String description,
			String shortDescription,
			Product product);
}
