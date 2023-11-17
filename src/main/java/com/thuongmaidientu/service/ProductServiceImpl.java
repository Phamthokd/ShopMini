package com.thuongmaidientu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.thuongmaidientu.model.Category;
import com.thuongmaidientu.model.Product;
import com.thuongmaidientu.model.User;
import com.thuongmaidientu.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired 
	private StorageService storageService;
	
	@Autowired 
	private CategoryService categoryService;
	

	@Override
	public List<Product> getAll() {			
		return this.productRepository.findAll();
	}

	@Override
	public Product create(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product update(Product product) {
		// TODO Auto-generated method stub
		return productRepository.save(product);
	}

	@Override
	public Boolean delete(Long id) {
		try {
			this.productRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Product findById(Long id) {
		return this.productRepository.findById(id).get();
	}

	@Override
	public List<Product> findByUserProduct(User userProduct) {		
		return this.productRepository.findByUserProduct(userProduct);
	}

	@Override
	public void processFile(MultipartFile file1, MultipartFile file2, MultipartFile file3, MultipartFile file4,String description, String shortDescription,Product product) {
		this.storageService.store(file1);
		this.storageService.store(file2);
		this.storageService.store(file3);
		this.storageService.store(file4);
		String fileName1 = file1.getOriginalFilename();
		String fileName2 = file2.getOriginalFilename();
		String fileName3 = file3.getOriginalFilename();
		String fileName4 = file4.getOriginalFilename();
		product.setImage1(fileName1);
		product.setImage2(fileName2);
		product.setImage3(fileName3);
		product.setImage4(fileName4);	
		
		product.setDescription(description);
		product.setShortDescription(shortDescription);
	}

	@Override
	public void processCategory(Category category,String categoryInput,Product product) {
		if (category == null) {

			Category categoryNew = new Category();
			categoryNew.setName(categoryInput);

			product.setCategory(categoryService.create(categoryNew));
		} else {
			product.setCategory(category);
		}
		
	}

	@Override
	public void processFileEdit(MultipartFile file1, MultipartFile file2, MultipartFile file3, MultipartFile file4,
			String description, String shortDescription, Product product) {
		
		String fileName1 = file1.getOriginalFilename();
		String fileName2 = file2.getOriginalFilename();
		String fileName3 = file3.getOriginalFilename();
		String fileName4 = file4.getOriginalFilename();
		boolean isEmpty1 = fileName1 == null || fileName1.trim().length() == 0;
		if (!isEmpty1) {
			this.storageService.store(file1);
			product.setImage1(fileName1);
		}

		boolean isEmpty2 = fileName2 == null || fileName2.trim().length() == 0;
		if (!isEmpty2) {
			this.storageService.store(file2);
			product.setImage2(fileName2);
		}

		boolean isEmpty3 = fileName3 == null || fileName3.trim().length() == 0;
		if (!isEmpty3) {
			this.storageService.store(file3);
			product.setImage3(fileName3);
		}

		boolean isEmpty4 = fileName4 == null || fileName4.trim().length() == 0;
		if (!isEmpty4) {
			this.storageService.store(file4);
			product.setImage4(fileName4);
		}

		if(description.trim().length()!=0||description!=null) {
			product.setDescription(description);
		}
		if(shortDescription.trim().length()!=0 || shortDescription!=null) {
			product.setShortDescription(shortDescription);
		}
		
		
	}

}