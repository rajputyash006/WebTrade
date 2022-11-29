package com.webtrade.servicesImpls;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webtrade.dtos.AdminLoginDTO;
import com.webtrade.exceptions.LogException;
import com.webtrade.models.Admin;
import com.webtrade.models.CurrentAdminSession;
import com.webtrade.repositorys.AdminLogRepo;
import com.webtrade.repositorys.AdminRepo;
import com.webtrade.services.AdminLogService;

@Service
public class AdminLogServiceImpl implements AdminLogService {

	@Autowired
	private AdminRepo aRepo;
	
	@Autowired
	private AdminLogRepo adminLogRepo;
	
	@Override
	public String adminLogIn(AdminLoginDTO dto) throws LogException {
		
		Admin admin = aRepo.findByAdminUserName(dto.getAdminUserName());
		
		if(admin == null) throw new LogException("Please Enter a valid admin UserName.");
		
		if(admin.getAdminPassword().equals(dto.getPassword()))
		{
			Optional<CurrentAdminSession> cAdminSession = adminLogRepo.findById(admin.getAdminId());
			
			if(cAdminSession.isPresent()) throw new LogException("You are already LoggedIn with this UserName "+dto.getAdminUserName());
			
			adminLogRepo.save(new CurrentAdminSession(admin.getAdminId(), "Admin", LocalDateTime.now()));
			
			return "You are loggedIn successfully.";
		}
		else
			throw new LogException("Please Enter a valid User name or Password.");
	}

	@Override
	public String adminLogOut(Integer adminId) throws LogException {
		
		Optional<CurrentAdminSession> cAdminSession = adminLogRepo.findById(adminId);
		
		if(cAdminSession.isPresent())
		{
			adminLogRepo.deleteById(adminId);
			
			return "You are successfully logged Out.";
		}
		else
			throw new LogException("Please provide a valid Id.");
	}

	

}