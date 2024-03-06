package com.cg.railwayreservation.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

//import com.cg.railwayreservation.booking.ExternalClasses.Payment;
import com.cg.railwayreservation.payment.exception.PaymentFailException;
import com.cg.railwayreservation.payment.exception.PaymentNotFoundWithIdException;
import com.cg.railwayreservation.payment.repository.PaymentRepository;
import com.cg.railwayreservation.payment.service.PaymentServiceImp;

@SpringBootTest
class PaymentApplicationTests {

}
