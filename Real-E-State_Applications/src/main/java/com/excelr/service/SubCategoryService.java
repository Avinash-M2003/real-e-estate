package com.excelr.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.excelr.model.Category;
import com.excelr.model.SubCategory;
import com.excelr.model.SubCategorydto;
import com.excelr.repo.CategoryRepo;
import com.excelr.repo.SubCategoryRepo;
import com.excelr.util.S3Util;

@Service
public class SubCategoryService {
	@Autowired
	private S3Util s3Util;
	@Autowired
	private SubCategoryRepo subCategoryRepo;
	@Autowired
	private CategoryRepo CategoryRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(SubCategoryService.class);
	
	public Page<SubCategorydto> getSubcategory(Pageable pageable){
		return subCategoryRepo.findAll(pageable).map(this::mapSubcategoryToSubcategoryDTO);
	}
	
	public SubCategorydto getSubCategoryById(Integer id) {
	    SubCategory subCategory = subCategoryRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("SubCategory not found with id: " + id));
	    return mapSubcategoryToSubcategoryDTO(subCategory);
	}

	
	public Page<SubCategorydto> getSubcategoryByCategory(Long categoryId, Pageable pageable) {
        return subCategoryRepo.findByCategoryId(categoryId, pageable)
                .map(this::mapSubcategoryToSubcategoryDTO);
    }
	
	
	public void deleteSubCategory(Integer id) {
		SubCategory subCategory = subCategoryRepo.findById(id)
   	            .orElseThrow(() -> new RuntimeException("SubCategory not found with id: " + id));
		try {
			if (subCategory.getImage() != null) {
				s3Util.deleteImage(subCategory.getImage());
   	        }
		} catch (Exception e) {
   	        	 logger.error("Failed to delete image from S3 for subCategory id: {}. Error: {}", id, e.getMessage());
   	    }
		subCategoryRepo.delete(subCategory);
   	    logger.info("Successfully deleted product with id: {}", id);
    }
	
	public SubCategorydto createSubCategory(SubCategorydto subCategorydto, MultipartFile image) {
        Category category = CategoryRepo.findByName(subCategorydto.getCategoryType())
       		 .orElseThrow(() -> new RuntimeException(
               "Category not found with id: "
           ));
           
       String imageUrl = s3Util.uploadImage(image);

       SubCategory subCategory = new SubCategory();
       subCategory.setFeature(subCategorydto.getFeature());
       subCategory.setLocation(subCategorydto.getLocation());
       subCategory.setImage(imageUrl); 
       subCategory.setCategory(category);
       SubCategory savedSubCategory = subCategoryRepo.save(subCategory);
       return mapSubcategoryToSubcategoryDTO(savedSubCategory);
   }
	
	 public SubCategorydto updateSubCategory(Integer id, SubCategorydto subCategorydto, MultipartFile image) {
		 SubCategory existingSubCategory = subCategoryRepo.findById(id)
	                .orElseThrow(() -> new RuntimeException(
	                    "SubCategory not found with id: " + id
	                ));
	            Category category = null;
	            if (subCategorydto.getCategoryType() != null) {
	                category = CategoryRepo.findByName(subCategorydto.getCategoryType())
	                    .orElseThrow(() -> new RuntimeException(
	                        "Subcategory not found with id: " + subCategorydto.getCategoryType()
	                    ));
	            }
	            String imageUrl = existingSubCategory.getImage();
	            if (image != null && !image.isEmpty()) {
	                try {
	                    imageUrl = s3Util.updateImage(existingSubCategory.getImage(), image);
	                } catch (Exception e) {
	                    throw new RuntimeException("Failed to update product image: " + e.getMessage());
	                }
	            }
	            existingSubCategory.setFeature(subCategorydto.getFeature() != null ? subCategorydto.getFeature() : existingSubCategory.getFeature());
	            existingSubCategory.setLocation(subCategorydto.getLocation() != null ? subCategorydto.getLocation() : existingSubCategory.getLocation());
	            existingSubCategory.setImage(imageUrl);
	            
	            if (category != null) {
	            	existingSubCategory.setCategory(category);
	            }
	            SubCategory updatedSubCategory = subCategoryRepo.save(existingSubCategory);
	            logger.info("Successfully updated product with id: {}", id);
	            return mapSubcategoryToSubcategoryDTO(updatedSubCategory);
	    }
	 
	 private SubCategorydto mapSubcategoryToSubcategoryDTO(SubCategory subCategory){
		    return new SubCategorydto(
		    		subCategory.getImage(),
		    		subCategory.getLocation(),
		            subCategory.getFeature(),
		            subCategory.getCategory()!=null ? subCategory.getCategory().getType():null
		    );
		}

}
