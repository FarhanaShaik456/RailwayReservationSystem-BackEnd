package com.cg.railwayreservation.help.model;

import java.time.LocalDate;



public class ExceptionResponse {
	private LocalDate timestamp;
	  private String message;
	  private String httpCodeMessage;
	public LocalDate getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDate timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getHttpCodeMessage() {
		return httpCodeMessage;
	}
	public void setHttpCodeMessage(String httpCodeMessage) {
		this.httpCodeMessage = httpCodeMessage;
	}
	public ExceptionResponse(LocalDate timestamp, String message, String httpCodeMessage) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.httpCodeMessage = httpCodeMessage;
	}
	  
	public ExceptionResponse() {
		
	}
}
