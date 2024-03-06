package com.cg.railwayreservation.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.railwayreservation.admin.exception.UserNotFoundException;
import com.cg.railwayreservation.admin.model.LoginModel;
import com.cg.railwayreservation.admin.repository.LoginRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ServiceImpl {

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	public List<LoginModel> getAllUsers() {
		log.info("Get All Users Method Started");
		List<LoginModel> list=new ArrayList<>();
		List<LoginModel> findAll = loginRepository.findAll();
		for(LoginModel login:findAll) {
			String role = login.getRole();
			if (role.equals("User")) {
			   log.info("Inside the if condition of getAllAdmins method in ServiceImpl Class");
			   list.add(login);
			}
		} 
		return list;
	}

	
	public LoginModel getByUsername(String username) {
		log.info("Get getByUsername Method is started");
		LoginModel login = loginRepository.findByUsername(username);
		if (login == null) {
			log.warn("Inside the if condition of getByUsername method in ServiceImpl Class");
			throw new UserNotFoundException("No data is found by these username " + username);
		} else {
			log.info("Inside the else condition of getByUsername method in ServiceImpl Class");
			return login;
		}
	}

	public LoginModel updatePassword(String username, LoginModel loginModel) {
		log.info("updateByUsername method started");
		LoginModel login = loginRepository.findByUsername(username);
		if (login == null) {
			log.warn("Inside the if condition of updateByUsername method in ServiceImpl Class");
			throw new UserNotFoundException("No data is found by these " + loginModel);
		} else {
			log.info("Inside the else condition of updateByUsername method in ServiceImpl Class");

			login.setPassword(bcryptEncoder.encode(loginModel.getPassword()));
			LoginModel updated = loginRepository.save(login);
			log.info("Admin updated successfully");
			return updated;

		}
	}

	
	public LoginModel updateByUserDetails(String username, LoginModel loginModel) {
		log.info("updateByUserDetails method started");
		LoginModel login = loginRepository.findByUsername(username);
		if (login == null) {
			log.warn("Inside the if condition of updateByUserDetails method in ServiceImpl Class");
			throw new UserNotFoundException("No data is found by these " + loginModel);
		} else {
			log.info("Inside the else condition of updateByUserDetails method in ServiceImpl Class");

//			login.setPassword(bcryptEncoder.encode(loginModel.getPassword()));
			login.setAge(loginModel.getAge());
			login.setEmail(loginModel.getEmail());
			login.setGender(loginModel.getGender());
			login.setCountry(loginModel.getCountry());
			LoginModel updated = loginRepository.save(login);
			log.info("Admin updated successfully");
			return updated;

		}
	}
	
	public String DeleteUser(String username) {
		
		loginRepository.deleteById(username);
		
		return "User Deleted successfully";
	}

}
