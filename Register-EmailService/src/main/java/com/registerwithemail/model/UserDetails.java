package com.registerwithemail.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "RegisterWithEmail")
public class UserDetails {

	@Id
	private String username;
	private String email;
	private String password;
	private String phoneNumber;
	private String role;
	private String gender;
	private String age;
	private String country;
	private boolean active;
	private String otp;
	private LocalDateTime otpGeneratedTime;

}
