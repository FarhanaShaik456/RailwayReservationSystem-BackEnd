package com.cg.railwayreservation.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookingNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookingNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookingNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	

}
