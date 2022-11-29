package com.webtrade.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webtrade.models.CurrentAdminSession;

public interface AdminLogRepo extends JpaRepository<CurrentAdminSession, Integer>{
	
	
}

