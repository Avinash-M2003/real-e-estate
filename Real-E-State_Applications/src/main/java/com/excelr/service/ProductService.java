package com.excelr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.excelr.model.Product;
import com.excelr.model.Productdto;
import com.excelr.model.SubCategory;
import com.excelr.repo.ProductRepo;
import com.excelr.repo.SubCategoryRepo;
import com.excelr.util.S3Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductService {
	
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private SubCategoryRepo subCategoryRepo;
    @Autowired
    private S3Util s3Util;
    
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	
	public Page<Productdto> getProducts(Pageable pageable){
		return productRepo.findAll(pageable).map(this::mapProductToProductDTO);
	}
	
	public Page<Productdto> getProductsBySubcategory(Long subcategoryId, Pageable pageable) {
        return productRepo.findBySubcategoryId(subcategoryId, pageable)
                .map(this::mapProductToProductDTO);
    }
	
	public Productdto getProductById(Integer id) {
    	Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return mapProductToProductDTO(product);
    }
	
	
	public void deleteProduct(Integer id) {
		Product product = productRepo.findById(id)
   	            .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
		try {
			if (product.getImage() != null) {
				s3Util.deleteImage(product.getImage());
   	        }
		} catch (Exception e) {
   	        	 logger.error("Failed to delete image from S3 for product id: {}. Error: {}", id, e.getMessage());
   	    }
   	    productRepo.delete(product);
   	    logger.info("Successfully deleted product with id: {}", id);
    }
	
	
	
	public Productdto createProduct(Productdto productdto, MultipartFile image) {
         SubCategory subcategory = subCategoryRepo.findByName(productdto.getSubCategoryFeature())
        		 .orElseThrow(() -> new RuntimeException(
                "Subcategory not found with id: "
            ));
            
        String imageUrl = s3Util.uploadImage(image);

        Product product = new Product();
        product.setName(productdto.getName());
        product.setPrice(productdto.getPrice());
        product.setDescription(productdto.getDescription());
        product.setImage(imageUrl); 
        product.setRating(productdto.getRating());
        product.setPaytype(productdto.getPaytype());
        product.setSubCategory(subcategory);
        product.setUser(null);

        Product savedProduct = productRepo.save(product);
        return mapProductToProductDTO(savedProduct);
    }
	
	 public Productdto updateProduct(Integer id, Productdto productdto, MultipartFile image) {
	    	Product existingProduct = productRepo.findById(id)
	                .orElseThrow(() -> new RuntimeException(
	                    "Product not found with id: " + id
	                ));
	            SubCategory subcategory = null;
	            if (productdto.getSubCategoryFeature() != null) {
	                subcategory = subCategoryRepo.findByName(productdto.getSubCategoryFeature())
	                    .orElseThrow(() -> new RuntimeException(
	                        "Subcategory not found with id: " + productdto.getSubCategoryFeature()
	                    ));
	            }
	            String imageUrl = existingProduct.getImage();
	            if (image != null && !image.isEmpty()) {
	                try {
	                    imageUrl = s3Util.updateImage(existingProduct.getImage(), image);
	                } catch (Exception e) {
	                    throw new RuntimeException("Failed to update product image: " + e.getMessage());
	                }
	            }
	            existingProduct.setName(productdto.getName() != null ? productdto.getName() : existingProduct.getName());
	            existingProduct.setPrice(productdto.getPrice() != 0 ? productdto.getPrice() : existingProduct.getPrice());
	            existingProduct.setDescription(productdto.getDescription() != null ? productdto.getDescription() : existingProduct.getDescription());
	            existingProduct.setImage(imageUrl);
	            existingProduct.setRating(productdto.getRating() != 0 ? productdto.getRating() : existingProduct.getRating());
	            
	            if (subcategory != null) {
	                existingProduct.setSubCategory(subcategory);
	            }
	            Product updatedProduct = productRepo.save(existingProduct);
	            logger.info("Successfully updated product with id: {}", id);
	            return mapProductToProductDTO(updatedProduct);
	    }
	 
	private Productdto mapProductToProductDTO(Product product){
	    return new Productdto(
	            product.getId(),
	            product.getImage(),
	            product.getName(),
	            product.getDescription(),
	            product.getPrice(),
	            product.getPaytype(),
	            product.getRating(),
	            product.getAddress(),
	            product.getSubCategory() != null ? product.getSubCategory().getFeature() : null,
	            product.getUser() != null ? product.getUser().getUsername() : null
	    );
	}
	
}
