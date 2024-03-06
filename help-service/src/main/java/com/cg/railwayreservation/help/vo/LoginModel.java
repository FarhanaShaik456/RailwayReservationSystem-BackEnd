package com.cg.railwayreservation.help.vo;

import lombok.Data;

@Data
public class LoginModel {

	private String username;
	private String password;
	private String role;
	private String email;
	private String gender;
	private Integer age;
	private String country;

}
