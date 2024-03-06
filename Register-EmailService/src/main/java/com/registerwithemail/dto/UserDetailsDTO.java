package com.registerwithemail.dto;

import lombok.Data;

@Data
public class UserDetailsDTO {

	private String username;
	private String email;
	private String password;
	private String country;
	private String role;
	private String gender;
	private String age;

}
