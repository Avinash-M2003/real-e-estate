package com.excelr.repo;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.excelr.model.Category;


public interface CategoryRepo extends JpaRepository<Category, Long> {
	Page<Category> findAll(Pageable pageable);
	Optional<Category> findByName(String name);
}
