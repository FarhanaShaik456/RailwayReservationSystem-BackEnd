package com.cg.railwayreservation.payment.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.railwayreservation.payment.exception.PaymentNotFoundWithIdException;
import com.cg.railwayreservation.payment.model.Payment;
import com.cg.railwayreservation.payment.service.PaymentService;

//@DataMongoTest
@SpringBootTest
public class PaymentRepositoryTest {
	
	 

	 
	@Mock
    private PaymentRepository paymentRepository;

 

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

 

    @Test
    public void testFindByBookingId() {
        // Mock data and behavior for findByBookingId
        String bookingId = "AB123";
        Payment payment = new Payment(/* Initialize with payment data */);
        when(paymentRepository.findByBookingId(bookingId)).thenReturn(payment);

        // Call the repository method
        Payment result = paymentRepository.findByBookingId(bookingId);

        verify(paymentRepository).findByBookingId(bookingId);
 
     
        assertNotNull(result);
        // Add more assertions to check the correctness of the retrieved payment.
 
 
    }
}
