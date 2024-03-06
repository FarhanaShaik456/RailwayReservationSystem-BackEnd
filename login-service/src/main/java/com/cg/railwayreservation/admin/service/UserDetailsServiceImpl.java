package com.cg.railwayreservation.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.railwayreservation.admin.exception.UserNotFoundException;
import com.cg.railwayreservation.admin.model.LoginModel;
import com.cg.railwayreservation.admin.repository.LoginRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	LoginRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UserNotFoundException {

		LoginModel user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UserNotFoundException("Please Enter valid username and password: " + username);
		}
		 else if (!user.isActive()) {
		    System.out.println("****************************************************************** your account is not verified");
			throw new UserNotFoundException("your account is not verified" + username);
		}
		return UserDetailsImpl.getUser(user);
	}

}