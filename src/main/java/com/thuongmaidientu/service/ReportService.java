package com.thuongmaidientu.service;

import java.util.List;

import com.thuongmaidientu.model.Product;
import com.thuongmaidientu.model.Report;

public interface ReportService {
	Report create(Report report);	
	List<Object[]> countReportsByProduct();
	List<Report> findByProduct(Product product);
}
