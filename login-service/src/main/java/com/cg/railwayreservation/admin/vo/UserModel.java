package com.cg.railwayreservation.admin.vo;

import lombok.Data;

@Data

public class UserModel {
	
	private String username;
	private String password;
	private String role;
	private String email;
	private String gender;
	private int age;
	private String country;
	
}
