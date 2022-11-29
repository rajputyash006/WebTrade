package com.webtrade.services;

import com.webtrade.dtos.UserLoginDTO;
import com.webtrade.exceptions.LogException;

public interface UserLogService {
	
	public String userLogIn(UserLoginDTO dto)throws LogException;

	public String userLogOut(Integer userId)throws LogException;
}
