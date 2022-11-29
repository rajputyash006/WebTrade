package com.webtrade.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Order {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
//	@SequenceGenerator(name = "order_seq", sequenceName = "Order_Id", allocationSize=1, initialValue=11111)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;

	@JsonIgnore
	private Integer total_Order_Amount;
	
	private String order_Payment_Status;

	private String order_Status;
	
	@JsonIgnore
	private LocalDate order_Date;
	
	@JsonIgnore
	private LocalTime order_Time;
	
	@JsonIgnore
	private LocalDate delivery_Date;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Product> products=new ArrayList<>();
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
}
















//@NotNull(message = "Order payment Status should not be null")
//@NotNull(message = "Order status should not be null")
//@NotBlank(message = "Order status should not be blank")
//@Size(min = 3,max = 20,message = "Order status should be minimum 3 and maximum 12 character")	
//@Min(value = 500,message = "Minimum total cart value is 500")
//@Max(value = 500000,message = "Maximum total cart value in single order is 500000")
