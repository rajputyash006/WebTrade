package com.webtrade.servicesImpls;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webtrade.exceptions.LogException;
import com.webtrade.exceptions.NoOrderFoundException;
import com.webtrade.exceptions.NoProductFoundException;
import com.webtrade.exceptions.UserNotFoundException;
import com.webtrade.models.CurrentUserSession;
import com.webtrade.models.Order;
import com.webtrade.models.Product;
import com.webtrade.models.User;
import com.webtrade.repositorys.OrderRepo;
import com.webtrade.repositorys.ProductRepo;
import com.webtrade.repositorys.UserLogRepo;
import com.webtrade.repositorys.UserRepo;
import com.webtrade.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	
	@Autowired
	private UserLogRepo ulRepo;
	
	private UserRepo uRepo;
	
	@Autowired
	private OrderRepo oRepo;
	
	@Autowired
	private ProductRepo pRepo;
	

	@Override
	public Order makeOrder(Integer userId, Order order) throws LogException, UserNotFoundException {

		Optional<CurrentUserSession> cusess = ulRepo.findById(userId);
		
		if(cusess.isPresent())
		{
			Optional<User> us= uRepo.findById(userId);
			List<Product> cartProd= order.getProducts();
			
			int totalAmount=0;
			
			for(Product p:cartProd) {
				totalAmount+=((p.getProductPrice())*(p.getProductPrice()));
			}
			
			Order newOrder=new Order();
			newOrder.setOrder_Payment_Status(order.getOrder_Payment_Status());
			newOrder.setOrder_Status(order.getOrder_Status());
			newOrder.setOrder_Date(LocalDate.now());
			newOrder.setOrder_Time(LocalTime.now());
			newOrder.setDelivery_Date(LocalDate.now().plusDays(10));
			newOrder.setProducts(cartProd);
			newOrder.setTotal_Order_Amount(totalAmount);
			if(us.isPresent()) {
				newOrder.setUser(us.get());
			}
			else {
				throw new UserNotFoundException("Invalid user id");
			}
			
			return newOrder;
			
		}
		else
			throw new LogException("No user found login required");
	}

	@Override
	public List<Product> getAllProducts(Integer userId, String productName) throws LogException, NoProductFoundException {

		Optional<CurrentUserSession> cusess = ulRepo.findById(userId);
		
		if(cusess.isPresent())
		{
			List<Product> products= pRepo.findByProductName(productName);
			if(products.size()!=0) {
				return products;
				}
			else {
				throw new NoProductFoundException("No product found with product name "+productName);
				}
		}
		else
			throw new LogException("No user found login required");
	}

	@Override
	public List<Product> getProductsOfCat(Integer userId, String category) throws LogException, NoProductFoundException {

		Optional<CurrentUserSession> cusess = ulRepo.findById(userId);
		
		if(cusess.isPresent()){
			List<Product> products= pRepo.findByProductCategory(category);
			if(products.size()!=0) {
				return products;
			}
			else {
				throw new NoProductFoundException("No product found with product name "+category);
			}
		}
		else
			throw new LogException("No user found login required");
	}

	@Override
	public List<Product> getProductsByFilter(Integer userId, double lowRating, double highRating, Integer lowPrice,Integer highPrice) throws LogException, NoProductFoundException {

		Optional<CurrentUserSession> cusess = ulRepo.findById(userId);
		
		if(cusess.isPresent()){
			List<Product> products= pRepo.getProductsByFilter(lowRating, highRating, lowPrice, highPrice);
			if(products.size()!=0) {
				return products;
			}
			else {
				throw new NoProductFoundException("No product found with filter range");
			}
		}
		else
			throw new LogException("No user found login required");
	}

	@Override
	public List<Order> getAllOrders(Integer userId) throws LogException, NoProductFoundException {

		Optional<CurrentUserSession> cusess = ulRepo.findById(userId);
		
		if(cusess.isPresent()){
			List<Order> orders= oRepo.getAllOrdersOfUser(userId);
			if(orders.size()!=0) {
				return orders;
			}
			else {
				throw new NoProductFoundException("No product found with filter range");
			}
		}
		else
			throw new LogException("No user found login required");
	}

	@Override
	public String getOrderStatus(Integer userId, Integer orderId) throws LogException, NoOrderFoundException {

		Optional<CurrentUserSession> cusess = ulRepo.findById(userId);
		
		if(cusess.isPresent()){
			Optional<Order> opt= oRepo.findById(orderId);
			if(opt.isPresent()) {
				Order order= opt.get();
				String orderStatus= order.getOrder_Status();
				return orderStatus;
			}
			else {
				throw new NoOrderFoundException("No order found with order id "+orderId);
			}
		}
		else
			throw new LogException("No user found login required");
	}

}
