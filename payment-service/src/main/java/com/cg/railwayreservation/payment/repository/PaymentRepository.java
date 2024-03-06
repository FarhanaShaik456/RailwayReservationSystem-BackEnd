package com.cg.railwayreservation.payment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cg.railwayreservation.payment.model.Payment;

//import com.cg.railwayreservation.booking.ExternalClasses.Payment;

public interface PaymentRepository extends MongoRepository<Payment, Integer> {

	//boolean existsByTransactionId(int transactionId);
	
	Payment findByBookingId(String bookingId);
	

}
