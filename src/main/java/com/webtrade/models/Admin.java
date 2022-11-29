package com.webtrade.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Admin {
	
	@Id
	@Column(unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer adminId;
	private String adminUserName;
	private String adminPassword;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Product> products=new ArrayList<>();
	
	
	public Admin(String adminUserName, String adminPassword) {
		super();
		this.adminUserName = adminUserName;
		this.adminPassword = adminPassword;
	}
	
	
	//insert into admin values(1,'YashRajput','yash1234');
}
