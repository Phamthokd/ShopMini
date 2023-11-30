package com.thuongmaidientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thuongmaidientu.model.OrderDetails;

public interface OrderDetailRepository extends JpaRepository<OrderDetails, Long> {

}
