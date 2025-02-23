package com.excelr.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excelr.model.SubCategory;
@Repository
public interface SubCategoryRepo extends JpaRepository<SubCategory, Integer> {
	Optional<SubCategory> findByName(String name);
	
	Page<SubCategory> findByCategoryId(Long categoryId, Pageable pageable);

}
