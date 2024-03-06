package com.registerwithemail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.registerwithemail.dto.LoginDTO;
import com.registerwithemail.dto.UserDetailsDTO;
import com.registerwithemail.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200",methods = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST})
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register/addUser")
  public ResponseEntity<String> register(@RequestBody UserDetailsDTO registerDto) {
    return new ResponseEntity<>(userService.register(registerDto), HttpStatus.OK);
  }

  @PutMapping("/verify-account/{email}/{otp}")
  public ResponseEntity<String> verifyAccount(@PathVariable String email,
      @PathVariable String otp) {
    return new ResponseEntity<>(userService.verifyAccount(email, otp), HttpStatus.OK);
  }
  @PutMapping("/regenerate-otp/{email}")
  public ResponseEntity<String> regenerateOtp(@PathVariable String email) {
    return new ResponseEntity<>(userService.regenerateOtp(email), HttpStatus.OK);
  }
  @PutMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginDTO loginDto) {
    return new ResponseEntity<>(userService.login(loginDto), HttpStatus.OK);
  }
  
  @PutMapping("/forgot-password")
  public ResponseEntity<String> forgotpassword(@RequestParam String email){
	  return new ResponseEntity<>(userService.forgotPassword(email), HttpStatus.OK);
  }
  @PutMapping("/set-password")
  public ResponseEntity<String> setPassword(@RequestParam String email, @RequestParam String newPassword){
	  return new ResponseEntity<>(userService.setPassword(email, newPassword), HttpStatus.OK);
  }
}
