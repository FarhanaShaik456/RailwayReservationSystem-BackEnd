package com.cg.railwayreservation.booking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Booking")
public class BookingModel {

	@Id
	private String pnr;

	private String username;

	@NotBlank(message = "Train number is required")
	private String trainNo;

	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "\\d{10}", message = "Phone number must be a 10-digit number")
	private String phnnumber;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

	@Min(value = 1, message = "Number of tickets must be at least 1")
	@Max(value = 6, message = "You can't book more than 6 tickets at a time")
	private int numberOfTickets;

	private int Cost;

}
