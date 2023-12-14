package com.thuongmaidientu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thuongmaidientu.model.Product;
import com.thuongmaidientu.model.Report;
import com.thuongmaidientu.repository.ReportRepository;
import com.thuongmaidientu.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private ReportRepository reportRepository;

	@Override
	public Report create(Report report) {		
		return reportRepository.save(report);
	}


	@Override
	public List<Object[]> countReportsByProduct() {
		// TODO Auto-generated method stub
		 return reportRepository.countReportsByProduct();
	}



	@Override
	public List<Report> findByProduct(Product product) {
		// TODO Auto-generated method stub
		return reportRepository.findByProduct(product);
	}

}
