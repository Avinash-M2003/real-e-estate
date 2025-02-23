package com.excelr.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.excelr.model.SubCategorydto;
import com.excelr.service.SubCategoryService;

@RestController
@RequestMapping("/api/subcategories")
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping
    public ResponseEntity<Page<SubCategorydto>> getSubcategories(Pageable pageable) {
        Page<SubCategorydto> subcategories = subCategoryService.getSubcategory(pageable);
        return ResponseEntity.ok(subcategories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubCategorydto> getSubcategoryById(@PathVariable Integer id) {
        return ResponseEntity.ok(subCategoryService.getSubCategoryById(id));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Page<SubCategorydto>> getSubcategoriesByCategory(@PathVariable Long categoryId, Pageable pageable) {
        Page<SubCategorydto> subcategories = subCategoryService.getSubcategoryByCategory(categoryId, pageable);
        return ResponseEntity.ok(subcategories);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SubCategorydto> createSubcategory(@RequestPart("subcategory") SubCategorydto subCategoryDto,
            @RequestPart("image") MultipartFile image) {
        return ResponseEntity.ok(subCategoryService.createSubCategory(subCategoryDto, image));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SubCategorydto> updateSubcategory(@PathVariable Integer id,
            @RequestPart(value = "subcategory") SubCategorydto subCategoryDto,
            @RequestPart(value = "image", required = false) MultipartFile image) {
        return ResponseEntity.ok(subCategoryService.updateSubCategory(id, subCategoryDto, image));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubcategory(@PathVariable Integer id) {
        subCategoryService.deleteSubCategory(id);
        return ResponseEntity.noContent().build();
    }
}
