package com.thuongmaidientu.service;

import java.util.List;

import com.thuongmaidientu.model.Review;

public interface ReviewService {
	Review create(Review review);
	
	List<Review> findByProductId(Long productId);
}
