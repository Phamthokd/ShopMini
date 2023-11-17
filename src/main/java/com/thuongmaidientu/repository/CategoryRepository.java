package com.thuongmaidientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.thuongmaidientu.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	@Query(value = "Select c from Category c where name = :name")
	Category findByName(String name);
}
