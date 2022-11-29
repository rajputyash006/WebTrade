package com.webtrade.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webtrade.models.Admin;
import com.webtrade.models.Product;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer>{
	
	public Admin findByAdminUserName(String adminUserName);
	
}