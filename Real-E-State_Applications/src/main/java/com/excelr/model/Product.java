package com.excelr.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Product {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String image;
	private String name;
	@Column(length = 1000)
	private String description;
	private double price;
	private String paytype;
	private double rating;
	private String address;
	@ManyToOne
	@JoinColumn(name = "subCategory_id", nullable = false)
	private SubCategory subCategory;
	@OneToOne
	@JoinColumn(name = "user_id", nullable=false)
	private User user;
}
