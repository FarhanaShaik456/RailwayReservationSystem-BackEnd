package com.cg.railwayreservation.train.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TrainNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TrainNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TrainNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
    
}
