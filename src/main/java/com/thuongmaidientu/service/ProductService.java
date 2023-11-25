package com.thuongmaidientu.service;

import java.util.List;

import org.springframework.data.domain.Page;
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
	List<Product> findByUserProduct(User userProduct);
	List<Product> findByCategory(Category category);
	List<Product> searchProducts(String keyword);
	Page<Product> getAll(Integer pageNo);
	Page<Product> searchProducts(String keyword,Integer pageNo);
	Page<Product> findByCategory(Category category,Integer pageNo);
	Page<Product> findByUserProduct(User userProduct,Integer pageNo);
}
