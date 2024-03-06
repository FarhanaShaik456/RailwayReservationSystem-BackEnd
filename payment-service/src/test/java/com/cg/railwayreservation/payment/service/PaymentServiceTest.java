package com.cg.railwayreservation.payment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.railwayreservation.payment.exception.PaymentFailException;
import com.cg.railwayreservation.payment.exception.PaymentNotFoundWithIdException;
import com.cg.railwayreservation.payment.model.Payment;
import com.cg.railwayreservation.payment.repository.PaymentRepository;

@SpringBootTest
public class PaymentServiceTest {
	

    @InjectMocks
    private PaymentServiceImp paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Test
    public void testDoPayment_Success() throws Exception {
    	//throws PaymentFailException {
        String userName = "user123";
        String bookingId = "booking123";
        double amount = 500.0;

        Payment payment = new Payment();
        payment.setTransactionId(123);
        payment.setUsername(userName);
        payment.setAmount(amount);
        payment.setTransactionStatus("Payment Successfull");
        payment.setBookingId(bookingId);

        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment result = paymentService.doPayment(userName, bookingId, amount);

        assertNotNull(result);
        assertEquals(userName, result.getUsername());
        assertEquals(amount, result.getAmount());
        assertEquals("Payment Successfull", result.getTransactionStatus());
        assertEquals(bookingId, result.getBookingId());
    }
    
    @Test
    public void testDoPayment_Failure() {
        String userName = "user123";
        String bookingId = "booking123";
        double amount = 500.0;

        when(paymentRepository.save(any(Payment.class))).thenThrow(new RuntimeException("Simulated Payment Failure"));

        assertThrows(PaymentFailException.class, () -> {
            paymentService.doPayment(userName, bookingId, amount);
        });
    }
    
    @Test
    public void testGetPayment_Success() throws PaymentNotFoundWithIdException {
        String bookingId = "booking123";

        Payment payment = new Payment();
        payment.setBookingId(bookingId);

        when(paymentRepository.findByBookingId(bookingId)).thenReturn(payment);

        Payment result = paymentService.getPayment(bookingId);

        assertNotNull(result);
        assertEquals(bookingId, result.getBookingId());
    }

    @Test
    public void testGetPayment_PaymentNotFound() {
        String bookingId = "nonexistentBookingId";

        when(paymentRepository.findByBookingId(bookingId)).thenReturn(null);

        assertThrows(PaymentNotFoundWithIdException.class, () -> {
            paymentService.getPayment(bookingId);
        });
    }
    
    @Test
    void testGetAllPayment() {
        // Mock data
        Payment payment1 = new Payment();
        payment1.setTransactionId(1);
        payment1.setBookingId("Booking1");
        payment1.setUsername("User1");
        payment1.setAmount(100.0);
        payment1.setTransactionStatus("Success");

        Payment payment2 = new Payment();
        payment2.setTransactionId(2);
        payment2.setBookingId("Booking2");
        payment2.setUsername("User2");
        payment2.setAmount(150.0);
        payment2.setTransactionStatus("Pending");

        List<Payment> mockPayments = new ArrayList<>();
        mockPayments.add(payment1);
        mockPayments.add(payment2);

        // Define behavior for the mock repository
        when(paymentRepository.findAll()).thenReturn(mockPayments);

        // Call the service method
        List<Payment> result = paymentService.getallpayment();

        // Assertions
        assertNotNull(result);
        assertEquals(2, result.size()); // Ensure that two payments were returned
        assertEquals(payment1.getTransactionId(), result.get(0).getTransactionId());
        assertEquals(payment2.getTransactionId(), result.get(1).getTransactionId());

        // Verify that the repository's findAll method was called
//        verify(paymentRepository, times(1)).findAll();
    }

}
