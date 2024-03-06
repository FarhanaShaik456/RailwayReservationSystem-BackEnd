package com.registerwithemail.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendOtpEmail(String email, String otp) throws MessagingException {
	    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
	    mimeMessageHelper.setTo(email);
	    mimeMessageHelper.setSubject("Verify OTP");

	    String bodyText = String.format("""
	        <html>
	          <body style="font-family: Arial, sans-serif;">
	            <p>Hello,</p>
	            <p>Welcome to our Railway Reservation System!</p>
	            <p>We are delighted to offer online ticket booking services.</p>
	            <p>Please use the following OTP to verify your account:</p>
	            <p style="color: red; font-weight: bold;">%s</p>
	            <p style="color: blue;">Kindly note that this OTP will expire within 1 minute for security reasons.</p>
	          </body>
	        </html>
	        """, otp, email, otp);

	    mimeMessageHelper.setText(bodyText, true);
	    javaMailSender.send(mimeMessage);
	}


	public void sendSetPasswordEmail(String email) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
		mimeMessageHelper.setTo(email);
		mimeMessageHelper.setSubject("Set Password");
		mimeMessageHelper.setText("""
				               <div style="font-family: Arial, sans-serif;">
				  <p>Hello,</p>
				  <p>You have requested to set a new password for your account.</p>
				  <p>Please click on the link below to set your password:</p>
				  <p><a href="http://localhost:4200/set-password?email=%s" target="_blank">Set Password</a></p>
				  <p>If you did not request this action, please ignore this email.</p>
				  <p>Thank you,</p>
				  <p>Your Railway Reservation System Team</p>
				</div>

				                """.formatted(email), true);
		javaMailSender.send(mimeMessage);
	}
}
