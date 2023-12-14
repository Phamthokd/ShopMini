package com.thuongmaidientu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.thuongmaidientu.model.Report;
import com.thuongmaidientu.model.Product;


public interface ReportRepository extends JpaRepository<Report, Long> {

	@Query("SELECT r.product, COUNT(r) FROM Report r GROUP BY r.product")
	List<Object[]> countReportsByProduct();
	
	List<Report> findByProduct(Product product);

}
