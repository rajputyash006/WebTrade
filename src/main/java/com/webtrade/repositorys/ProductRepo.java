package com.webtrade.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.webtrade.models.Order;
import com.webtrade.models.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer>{
	
	public List<Product> findByProductName(String productName);
	
	public List<Product> findByProductCategory(String ProductCategory);
	
	@Query("select from Product where productRating>=?1 AND productRating<=?2 AND productPrice>=?3 AND productPrice<=?3")
	public List<Product> getProductsByFilter(double lowRating, double highRating, Integer lowPrice,Integer highPrice);
	
	
}