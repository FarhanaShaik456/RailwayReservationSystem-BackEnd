package com.cg.railwayreservation.admin.controller;


import jakarta.validation.Valid;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.railwayreservation.admin.jwt.JwtUtility;
import com.cg.railwayreservation.admin.model.LoginModel;
import com.cg.railwayreservation.admin.request.LoginRequest;
import com.cg.railwayreservation.admin.response.JSONResponse;
import com.cg.railwayreservation.admin.service.UserDetailsImpl;
import com.cg.railwayreservation.admin.service.UserDetailsServiceImpl;


@RestController
@RequestMapping("/registration")

public class AuthController {
	
	@Autowired
	DaoAuthenticationProvider authenticationManager;
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	JwtUtility jwtUtility;
	
	@PostMapping("/signin")
	public ResponseEntity<?> validateUser(@Valid @RequestBody LoginRequest loginRequest) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		String jwtToken = jwtUtility.generateToken(authentication);
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
	    List<String> collect = authorities.stream().map(GrantedAuthority :: getAuthority).collect(Collectors.toList());
	    JSONResponse jsonResponse = new JSONResponse(jwtToken, userDetails.getUsername(), collect);
		return ResponseEntity.ok(jsonResponse);
						
												
	}
	
	
}
