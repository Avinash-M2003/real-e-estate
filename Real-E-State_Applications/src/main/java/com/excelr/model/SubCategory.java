package com.excelr.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SubCategory {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String image;
	private String location;
	private String feature;
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
	@OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Product> product;
}
