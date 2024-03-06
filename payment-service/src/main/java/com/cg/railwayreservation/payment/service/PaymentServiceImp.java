package com.cg.railwayreservation.payment.service;

import java.util.List;
import java.util.Optional;


import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.cg.railwayreservation.booking.ExternalClasses.Payment;
import com.cg.railwayreservation.payment.VO.BookingModel;
import com.cg.railwayreservation.payment.exception.PaymentFailException;
import com.cg.railwayreservation.payment.exception.PaymentNotFoundWithIdException;
import com.cg.railwayreservation.payment.model.Payment;
import com.cg.railwayreservation.payment.proxy.BookingProxy;
import com.cg.railwayreservation.payment.repository.PaymentRepository;

@Service
public class PaymentServiceImp implements PaymentService {

    @Autowired
    PaymentRepository repository;
    
    @Autowired
    BookingProxy proxy;
    
    @Override
    public Payment doPayment(String userName,String bookingId,double amount) throws PaymentFailException {
        boolean paymentDone = false;
       // double amoumt=0;
        try {
            Payment payment = new Payment();
            Random random = new Random();

//            BookingModel bm=proxy.viewByUserName(userName);
//            amoumt=bm.getCost();
            // Generate a random number between 100 and 1000 (inclusive)
            int randomNumber = random.nextInt(901) + 100;
            System.out.println(randomNumber);
            String tractionId = "" + randomNumber;
            int id = Integer.parseInt(tractionId);
            payment.setTransactionId(id);
            payment.setUsername(userName);
            payment.setAmount(amount);
            payment.setTransactionStatus("Payment Successfull");
            payment.setBookingId(bookingId);
            paymentDone = true;
            return repository.save(payment);

        } catch (Exception e) {
            // TODO: handle exception

            throw new PaymentFailException("Payment Failed of RS " + amount);

        }

    }

    @Override
    public Payment getPayment(String bookingId) throws PaymentNotFoundWithIdException {
        // TODO Auto-generated method stub

        Payment optionalPayment = repository.findByBookingId(bookingId);

        if (optionalPayment!=null) {

            return optionalPayment;

        } else {
            throw new PaymentNotFoundWithIdException("Payment not found with transactionId " + bookingId);
        }
    }
    
    @Override
    public List<Payment> getallpayment()
    {
    	List<Payment> p=repository.findAll();
    	return p;
    }

}
