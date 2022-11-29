package com.webtrade.services;

import com.webtrade.dtos.AdminLoginDTO;
import com.webtrade.exceptions.LogException;

public interface AdminLogService {
	
	public String adminLogIn(AdminLoginDTO dto)throws LogException;

	public String adminLogOut(Integer adminId)throws LogException;
}
