package com.thuongmaidientu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thuongmaidientu.model.Commission;
import com.thuongmaidientu.model.User;
import com.thuongmaidientu.repository.CommissionRepository;
import com.thuongmaidientu.service.CommissionService;

@Service
public class CommissionServiceImpl implements CommissionService {
	@Autowired
	private CommissionRepository commissionRepository;
	
	

	@Override
	public void saveCommissions() {
		List<Commission> listCommissions = new ArrayList<>();
		List<Object[]> infoShop = commissionRepository.infoShop();
		
		for (Object[] objects : infoShop) {
			User shop = (User) objects[0];
			Double totalCommission = (Double) objects[1];
			Integer month = (Integer) objects[2];
			if(commissionRepository.findByUserAndMonth(shop, month) == null) {
				Commission commission = new Commission();
				commission.setUser(shop);
				commission.setTotalCommission(totalCommission);
				commission.setStatus("Đang xử lý");				
				commission.setMonth(month);
				commissionRepository.save(commission);
				listCommissions.add(commission);
			}else {
				Commission commission = commissionRepository.findByUserAndMonth(shop, month);
				commission.setTotalCommission(totalCommission);
				commissionRepository.save(commission);
				listCommissions.add(commission);
			}
		}
		
	}



	@Override
	public List<Commission> findByStatusOrStatus() {
		saveCommissions();
		return commissionRepository.findByStatusOrStatusOrStatus("Đang xử lý", "Thanh toán","Chờ thanh toán");
	}



	@Override
	public Commission update(Commission commission) {
		// TODO Auto-generated method stub
		return commissionRepository.save(commission);
	}



	@Override
	public List<Commission> findByUserAndStatus(User user, String status) {
		// TODO Auto-generated method stub
		return commissionRepository.findByUserAndStatus(user, status);
	}



	@Override
	public Commission findById(Long id) {
		// TODO Auto-generated method stub
		return commissionRepository.findById(id).get();
	}
	
	

}
