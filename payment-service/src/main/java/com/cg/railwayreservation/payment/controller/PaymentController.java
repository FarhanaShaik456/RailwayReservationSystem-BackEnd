package com.cg.railwayreservation.payment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import com.cg.railwayreservation.booking.ExternalClasses.Payment;
import com.cg.railwayreservation.payment.exception.PaymentNotFoundWithIdException;
import com.cg.railwayreservation.payment.model.Payment;
import com.cg.railwayreservation.payment.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	PaymentService service;
	
	@GetMapping("/doPayment/{userName}/{bookingId}/{amount}")
	public Payment doPayment(@PathVariable String userName,@PathVariable String bookingId,@PathVariable double amount) throws Exception {
		return service.doPayment(userName,bookingId,amount);
	}
	@GetMapping("/getByTransactionId/{bookingId}")
	public ResponseEntity<Payment> getPayment(@PathVariable String bookingId) throws PaymentNotFoundWithIdException {
		Payment payment= service.getPayment(bookingId);
		return new ResponseEntity<Payment>(payment,HttpStatus.OK);
	}
	@GetMapping("/getAllPayment")
	public List<Payment> getAllPayments()
	{
		List<Payment> paymentlist=service.getallpayment();
		return paymentlist;
	}
		
	
	
	

}
