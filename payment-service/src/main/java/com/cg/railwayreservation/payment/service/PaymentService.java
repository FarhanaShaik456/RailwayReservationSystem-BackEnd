package com.cg.railwayreservation.payment.service;

import java.util.List;

import org.springframework.stereotype.Service;

//import com.cg.railwayreservation.booking.ExternalClasses.Payment;
import com.cg.railwayreservation.payment.exception.PaymentFailException;
import com.cg.railwayreservation.payment.exception.PaymentNotFoundWithIdException;
import com.cg.railwayreservation.payment.model.Payment;


public interface PaymentService {
	
    Payment doPayment(String userName,String bookingId,double amount) throws PaymentFailException;
	Payment getPayment(String bookingId) throws PaymentNotFoundWithIdException;
    List<Payment> getallpayment();

}
