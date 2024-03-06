package com.registerwithemail.service;

import org.springframework.stereotype.Service;

import com.registerwithemail.dto.LoginDTO;
import com.registerwithemail.dto.UserDetailsDTO;
import com.registerwithemail.model.UserDetails;
import com.registerwithemail.repository.UserRepository;
import com.registerwithemail.util.EmailUtil;
import com.registerwithemail.util.OtpUtil;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Service
public class UserService {

	@Autowired
	private OtpUtil otpUtil;
	@Autowired
	private EmailUtil emailUtil;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	public String register(UserDetailsDTO userDetailsDTO) {

		Object findByName = userRepository.findByUsername(userDetailsDTO.getUsername());
		if (findByName != null) {
			log.info("Username is already exists.");
			return "Username is already exists.";
		}
		Optional<UserDetails> findByEmail = userRepository.findByEmail(userDetailsDTO.getEmail());
		if (!findByEmail.isEmpty()) {
			return "Email is already Exists. please try with another email";
		}
		if(!userDetailsDTO.getRole().equals("Admin")&&!userDetailsDTO.getRole().equals("User")) {
			return "Define Correct Role";
		}
		
		String otp = otpUtil.generateOtp();
		try {
			emailUtil.sendOtpEmail(userDetailsDTO.getEmail(), otp);
		} catch (MessagingException e) {
			throw new RuntimeException("Unable to send otp please try again");
		}
		UserDetails user = new UserDetails();
		user.setUsername(userDetailsDTO.getUsername());
		user.setEmail(userDetailsDTO.getEmail());
		user.setPassword(bcryptEncoder.encode(userDetailsDTO.getPassword()));
//		user.setPassword(userDetailsDTO.getPassword());
		user.setCountry(userDetailsDTO.getCountry());
		user.setAge(userDetailsDTO.getAge());
		user.setGender(userDetailsDTO.getGender());
		user.setOtp(otp);
		user.setRole(userDetailsDTO.getRole());
		user.setOtpGeneratedTime(LocalDateTime.now());
		userRepository.save(user);
		return "User registration successful";
	}

	public String verifyAccount(String email, String otp) {
		UserDetails user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
		if (user.getOtp().equals(otp)
				&& Duration.between(user.getOtpGeneratedTime(), LocalDateTime.now()).getSeconds() < (1 * 60)) {
			user.setActive(true);
			userRepository.save(user);
			return "OTP verified you can login";
		}
		return "Please regenerate otp and try again";
	}

	public String regenerateOtp(String email) {
		UserDetails user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
		String otp = otpUtil.generateOtp();
		try {
			emailUtil.sendOtpEmail(email, otp);
		} catch (MessagingException e) {
			throw new RuntimeException("Unable to send otp please try again");
		}
		user.setOtp(otp);
		user.setOtpGeneratedTime(LocalDateTime.now());
		userRepository.save(user);
		return "Email sent... please verify account within 1 minute";
	}

	public String login(LoginDTO loginDto) {
		UserDetails user = userRepository.findByEmail(loginDto.getEmail())
				.orElseThrow(() -> new RuntimeException("User not found with this email: " + loginDto.getEmail()));
		if (!loginDto.getPassword().equals(user.getPassword())) {
			return "Password is incorrect";
		} else if (!user.isActive()) {
			return "your account is not verified";
		}
		return "Login successful";
	}

	public String forgotPassword(String email) {
		UserDetails user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
		try {
			emailUtil.sendSetPasswordEmail(email);
		} catch (MessagingException e) {
			throw new RuntimeException("Unable to send set password please try again");
		}
		return "Please check your email to set new password to your account.";
	}

	public String setPassword(String email, String newPassword) {
		UserDetails user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
		user.setPassword(bcryptEncoder.encode(newPassword));
//		user.setPassword(newPassword);
		userRepository.save(user);
		return "New Password set successfully login with new password";
	}

}
