package com.excelr.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.excelr.model.Category;
import com.excelr.model.Categorydto;
import com.excelr.repo.CategoryRepo;
import com.excelr.util.S3Util;

@Service
public class CategoryService {
	  @Autowired
	  private S3Util s3Util;
	  @Autowired
	  private CategoryRepo categoryRepo;
	  
	  private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	  
	 public Page<Categorydto> getCategoies(Pageable pageable){
		 return categoryRepo.findAll(pageable).map(this::mapCategoryToCategoryDTO);
	 }
	 
	 public Categorydto getCategoryById(Long id) {
		    Category category = categoryRepo.findById(id)
		            .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
		    return mapCategoryToCategoryDTO(category);
		}

	 
	 public void deleteCategory(Long id) {
			Category category = categoryRepo.findById(id)
	   	            .orElseThrow(() -> new RuntimeException("category not found with id: " + id));
			try {
				if (category.getImage() != null) {
					s3Util.deleteImage(category.getImage());
	   	        }
			} catch (Exception e) {
	   	        	 logger.error("Failed to delete image from S3 for product id: {}. Error: {}", id, e.getMessage());
	   	    }
			categoryRepo.delete(category);
	   	    logger.info("Successfully deleted product with id: {}", id);
	    }
	 
	 public Categorydto createCategory(Categorydto categorydto, MultipartFile image) {
            
        String imageUrl = s3Util.uploadImage(image);

        Category category = new Category();
        category.setImage(imageUrl);
        category.setType(categorydto.getType());
       
        Category savedProduct = categoryRepo.save(category);
        return mapCategoryToCategoryDTO(savedProduct);
    }
	 
	 public Categorydto updateCategory(Long id, Categorydto categorydto, MultipartFile image) {
		 	Category existingCategory = categoryRepo.findById(id)
	                .orElseThrow(() -> new RuntimeException(
	                    "Category not found with id: " + id
	                ));
	           
	            String imageUrl = existingCategory.getImage();
	            if (image != null && !image.isEmpty()) {
	                try {
	                    imageUrl = s3Util.updateImage(existingCategory.getImage(), image);
	                } catch (Exception e) {
	                    throw new RuntimeException("Failed to update product image: " + e.getMessage());
	                }
	            }
	            existingCategory.setType(categorydto.getType() != null ? categorydto.getType() : existingCategory.getType());
	            existingCategory.setImage(imageUrl);
	            
	            Category updatedCategory = categoryRepo.save(existingCategory);
	            logger.info("Successfully updated category with id: {}", id);
	            return mapCategoryToCategoryDTO(updatedCategory);
	    }
	  
	  private Categorydto mapCategoryToCategoryDTO(Category category){
		    return new Categorydto(
		    		category.getId(),
		    		category.getImage(),
		    		category.getType()
		    );
		}
}
