package com.excelr.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excelr.model.Product;
@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
	Page<Product> findBySubcategoryId(Long subcategoryId, Pageable pageable);
	
	Page<Product> findAll(Pageable pageable);
}
