package com.webtrade.servicesImpls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webtrade.exceptions.LogException;
import com.webtrade.exceptions.NoOrderFoundException;
import com.webtrade.models.CurrentAdminSession;
import com.webtrade.models.Order;
import com.webtrade.models.Product;
import com.webtrade.models.User;
import com.webtrade.repositorys.AdminLogRepo;
import com.webtrade.repositorys.OrderRepo;
import com.webtrade.repositorys.ProductRepo;
import com.webtrade.repositorys.UserRepo;
import com.webtrade.services.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private ProductRepo pRepo;
	
	@Autowired
	private OrderRepo oRepo;
	
	@Autowired
	private UserRepo uRepo;

	@Autowired
	private AdminLogRepo alRepo;
	
	
	@Override
	public Product addProduct(Integer adminId, Product product) throws LogException {
		

		Optional<CurrentAdminSession> casess = alRepo.findById(adminId);
		
		if(casess.isPresent())
		{
			Product savedProduct=pRepo.save(product);
			return savedProduct;
		}
		else
			throw new LogException("Admin login required to add product");
	}

	@Override
	public List<Order> getAllOrderDetails(Integer adminId)throws LogException, NoOrderFoundException {
		
		Optional<CurrentAdminSession> casess = alRepo.findById(adminId);
		if(casess.isPresent())
		{
			List<Order> orders=oRepo.getAllOrders(adminId);
			if(orders.size()>0) {
				return orders;
			}
			else {
				throw new NoOrderFoundException("No order found");
			}
		}
		else
			throw new LogException("Admin login required to get all orders details");
	}

	@Override
	public Order updateOrderStatus(Integer adminId,Integer orderId,String orderStatus) throws LogException, NoOrderFoundException{
		Optional<CurrentAdminSession> casess = alRepo.findById(adminId);
		if(casess.isPresent())
		{
			Optional<Order> opt= oRepo.findById(orderId);
			
			if(opt.isPresent()) {
				Order or= opt.get();
				or.setOrder_Status(orderStatus);
				Order sorder= oRepo.save(or);
				return sorder;
			}
			else
				throw new NoOrderFoundException("No order found with order id "+orderId);
		}
		else
			throw new LogException("Admin login required to get all orders details");
	}

	@Override
	public Order getOrderByEmail(Integer adminId, String email) throws LogException, NoOrderFoundException {
		Optional<CurrentAdminSession> casess = alRepo.findById(adminId);
		if(casess.isPresent())
		{
			Order or= oRepo.findByEmailId(email);
			if(or!=null) {
				return or;
			}
			else {
				throw new NoOrderFoundException("No order found with email id "+email);
			}
		}
		else
			throw new LogException("Admin login required to get all orders details by email id");
	}

	@Override
	public Order getAOrderDetails(Integer adminId, Integer orderId) throws LogException, NoOrderFoundException{
		Optional<CurrentAdminSession> casess = alRepo.findById(adminId);
		if(casess.isPresent())
		{
			Optional<Order> opt= oRepo.findById(orderId);
			
			if(opt.isPresent()) {
				Order or= opt.get();
				return or;
			}
			else
				throw new NoOrderFoundException("No order found with order id "+orderId);
		}
		else
			throw new LogException("Admin login required to get all orders details");
	}

	@Override
	public List<Order> getcancelOrRefundOrder(Integer adminId) throws LogException, NoOrderFoundException{
		Optional<CurrentAdminSession> casess = alRepo.findById(adminId);
		if(casess.isPresent())
		{
			List<Order> cancleOrders= oRepo.getCancledOrders(adminId);
			if(cancleOrders.size()!=0) {
				return cancleOrders;
			}
			else
			throw new NoOrderFoundException("No cancle order found");
		}
		else
			throw new LogException("Admin login required to get all orders details");
	}

	@Override
	public User getUserDetails(Integer adminId, Integer orderId) throws LogException, NoOrderFoundException{
		Optional<CurrentAdminSession> casess = alRepo.findById(adminId);
		if(casess.isPresent())
		{
			
			Order or= oRepo.findByOrderId(orderId);
			
			if(or!=null) {
				User user= uRepo.finByUserId(orderId);
				return user;
			}
			else 
				throw new NoOrderFoundException("No order found with order id "+orderId);
			
		}
		else
			throw new LogException("Admin login required to get all orders details");
	}

}
