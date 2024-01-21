package com.thuongmaidientu.service;

import java.util.List;

import com.thuongmaidientu.model.Commission;
import com.thuongmaidientu.model.User;

public interface CommissionService {
	void saveCommissions();
	Commission update(Commission commission);
	List<Commission> findByStatusOrStatus();
	List<Commission> findByUserAndStatus(User user, String status);
	Commission findById(Long id);	
	List<Commission> findByMonth(Integer month);
	List<Commission> findByMonthAndUser(Integer month, User user);
}
