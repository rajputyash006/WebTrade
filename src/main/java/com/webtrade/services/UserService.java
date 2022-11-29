package com.webtrade.services;

import java.util.List;

import com.webtrade.exceptions.LogException;
import com.webtrade.exceptions.NoOrderFoundException;
import com.webtrade.exceptions.NoProductFoundException;
import com.webtrade.exceptions.UserNotFoundException;
import com.webtrade.models.Order;
import com.webtrade.models.Product;

public interface UserService {
	
	public Order makeOrder(Integer userId,Order order) throws LogException, UserNotFoundException;
	
	public List<Product> getAllProducts(Integer userId,String productName) throws LogException, NoProductFoundException;
	
	public List<Product> getProductsOfCat(Integer userId,String category) throws LogException, NoProductFoundException;
	
	public List<Product> getProductsByFilter(Integer userId,double lowRating,double highRating,Integer lowPrice,Integer highPrice) throws LogException, NoProductFoundException;
	
	public List<Order> getAllOrders(Integer userId) throws LogException, NoProductFoundException;
	
	public String getOrderStatus(Integer userId,Integer orderId) throws LogException, NoOrderFoundException;
	
}
