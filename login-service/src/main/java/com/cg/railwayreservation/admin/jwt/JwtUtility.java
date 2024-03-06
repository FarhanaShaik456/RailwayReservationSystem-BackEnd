package com.cg.railwayreservation.admin.jwt;



import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.cg.railwayreservation.admin.service.UserDetailsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtUtility implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Value("${jwtSecret}")
	private String jwtSecret="my$ecretKey";

	@Value("${jwtExpirationMs}")
	private int jwtExpirationMs;

	public String generateToken(Authentication authentication) {

		 UserDetailsImpl userPrincipal =(UserDetailsImpl) authentication.getPrincipal();
		 List<String> roles = userPrincipal.getAuthorities()
		                  .stream()
		                  .map(GrantedAuthority::getAuthority)
		                  .collect(Collectors.toList());
		 
		String username = ((UserDetailsImpl) authentication.getPrincipal()).getUsername();
		Date now = new Date();
		return  Jwts.builder()
				.setSubject(username)
				.claim("roles", roles)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime()+jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}
	public boolean validateToken(String token) {
		parse(token);
		return true;
	}
    public String getUsername(String token) {
    	return parse(token).getBody().getSubject();
    }
	private Jws<Claims> parse(String token) {
		// TODO Auto-generated method stub
		return  Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
	}
	
}
