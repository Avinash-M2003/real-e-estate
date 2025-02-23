package com.excelr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Productdto {
	private Integer id;
	private String image;
	private String name;
	private String description;
	private double price;
	private String paytype;
	private double rating;
	private String address;
	private String subCategoryFeature;
	private String userName;
	
}
