package com.cg.railwayreservation.payment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.railwayreservation.payment.exception.PaymentNotFoundWithIdException;
import com.cg.railwayreservation.payment.model.Payment;
import com.cg.railwayreservation.payment.repository.PaymentRepository;
import com.cg.railwayreservation.payment.service.PaymentService;

@SpringBootTest
public class PaymentControllerTest {
	
    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @Test
    void testDoPayment() throws Exception {
        // Mock data
        String userName = "MockUser";
        String bookingId = "ab4m3co";
        double amount = 100.0;

        Payment payment = new Payment();
        payment.setTransactionId(1);
        payment.setBookingId(bookingId);
        payment.setUsername(userName);
        payment.setAmount(amount);
        payment.setTransactionStatus("Success");

        // Define behavior for the mock service
        when(paymentService.doPayment(userName, bookingId, amount)).thenReturn(payment);

        // Call the controller method
        Payment result = paymentController.doPayment(userName, bookingId, amount);

        // Assertions
        assertNotNull(result);
        assertEquals(payment.getTransactionId(), result.getTransactionId());
        assertEquals(payment.getBookingId(), result.getBookingId());
        assertEquals(payment.getUsername(), result.getUsername());
        assertEquals(payment.getAmount(), result.getAmount());
        assertEquals(payment.getTransactionStatus(), result.getTransactionStatus());
    }

    
    @Test
    void testGetAllPayments() {
        // Mock data
        List<Payment> expectedPayments = new ArrayList<>();
        Payment payment1 = new Payment();
        payment1.setTransactionId(1);
        payment1.setAmount(100.0);
        payment1.setBookingId("d445d");
        payment1.setUsername("dummy");
        payment1.setTransactionStatus("Success");


        Payment payment2 = new Payment();
        payment2.setTransactionId(2);
        payment2.setAmount(150.0);
        payment2.setBookingId("4959d");
        payment2.setUsername("mock");
        payment1.setTransactionStatus("Success");


        expectedPayments.add(payment1);
        expectedPayments.add(payment2);

        // Define behavior for the mock service
        when(paymentService.getallpayment()).thenReturn(expectedPayments);

        // Call the controller method
        List<Payment> actualPayments = paymentController.getAllPayments();

        // Assertions
        assertNotNull(actualPayments);
        assertEquals(expectedPayments.size(), actualPayments.size());
        assertEquals(expectedPayments.get(0).getTransactionId(), actualPayments.get(0).getTransactionId());
        assertEquals(expectedPayments.get(1).getAmount(), actualPayments.get(1).getAmount());
    }

    
    @Test
    public void testGetPayment() throws PaymentNotFoundWithIdException {
        // Mock the behavior of the service
        String bookingId = "123";
        Payment mockPayment = new Payment();
        when(paymentService.getPayment(bookingId)).thenReturn(mockPayment);

        // Call the controller method
        ResponseEntity<Payment> response = paymentController.getPayment(bookingId);

        // Verify that the service method was called with the correct parameter
        verify(paymentService).getPayment(bookingId);

        // Verify the response status code and the returned payment
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPayment, response.getBody());
    }

}
