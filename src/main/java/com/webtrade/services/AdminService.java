package com.webtrade.services;

import java.util.List;

import com.webtrade.exceptions.LogException;
import com.webtrade.exceptions.NoOrderFoundException;
import com.webtrade.exceptions.UserNotFoundException;
import com.webtrade.models.Order;
import com.webtrade.models.Product;
import com.webtrade.models.User;

public interface AdminService {
	
	public Product addProduct(Integer adminId, Product product) throws LogException;	
	
	public List<Order> getAllOrderDetails(Integer adminId) throws LogException, NoOrderFoundException;
	
	public Order updateOrderStatus(Integer adminId,Integer orderId,String orderStatus) throws LogException, NoOrderFoundException;
	
	public Order getOrderByEmail(Integer adminId,String email) throws LogException, NoOrderFoundException;
	
	public Order getAOrderDetails(Integer adminId,Integer orderId) throws LogException, NoOrderFoundException;
	
	public List<Order> getcancelOrRefundOrder(Integer adminId) throws LogException, NoOrderFoundException;
	
	public User getUserDetails(Integer adminId,Integer orderId) throws LogException,NoOrderFoundException;
	
	
}
