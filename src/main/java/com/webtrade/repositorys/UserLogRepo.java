package com.webtrade.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webtrade.models.CurrentUserSession;

public interface UserLogRepo extends JpaRepository<CurrentUserSession, Integer>{
	
	
}

