package com.webtrade.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	
	private String productName;
	
	private String productCategory;
	
	private Integer productPrice;
	
	private Integer productQuantities;
	
	private double productRating;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Admin admin;
	
}



















//@NotNull(message = "Product name should not be null")
//@NotBlank(message = "Product name should not be blank")
//@Size(min = 3,max = 20,message = "Product name should be minimum 3 and maximum 12 character")
//@NotNull(message = "Product category should not be null")
//@NotBlank(message = "Product category should not be blank")
//@Size(min = 3,max = 20,message = "Product category should be minimum 3 and maximum 12 character")	
//@Min(value = 50,message = "Product price must be more than 50 rupees")	
//@Min(value = 0,message = "Product quantity minimum is 0")	
//@Min(value = 1,message = "Minimum rating for product is 1")
//@Max(value = 5,message = "Maximum rating for product is 5")
//@NotNull(message = "Product category should not be null")
//@NotBlank(message = "Product category should not be blank")
