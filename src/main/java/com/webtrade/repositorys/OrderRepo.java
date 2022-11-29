package com.webtrade.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.webtrade.models.Order;

public interface OrderRepo extends JpaRepository<Order,Integer>{
	
	@Query("select from Order where adminId=?1")
	public List<Order> getAllOrders(Integer adminId);
	
	public Order findByOrderId(Integer orderId);
	
	public Order findByEmailId(String emailId);
	
	@Query("select from Order where adminId=?1 AND order_Status='cancle'")
	public List<Order> getCancledOrders(Integer adminId);
	
	@Query("select from Order where userId=?1")
	public List<Order> getAllOrdersOfUser(Integer userId);
}
