package com.webtrade.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webtrade.models.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	public User findByEmailId(String emailId);
	
	public User finByUserId(Integer UserId);
	
}
