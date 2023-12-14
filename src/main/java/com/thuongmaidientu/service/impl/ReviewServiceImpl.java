package com.thuongmaidientu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thuongmaidientu.model.Review;
import com.thuongmaidientu.repository.ReviewRepository;
import com.thuongmaidientu.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;

	@Override
	public Review create(Review review) {		
		return reviewRepository.save(review);
	}

	@Override
	public List<Review> findByProductId(Long productId) {
		// TODO Auto-generated method stub
		 return reviewRepository.findByProduct_Id(productId);
	}
	
}
