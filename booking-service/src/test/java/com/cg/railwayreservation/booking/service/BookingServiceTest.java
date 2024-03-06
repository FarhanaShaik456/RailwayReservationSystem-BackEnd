package com.cg.railwayreservation.booking.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.railwayreservation.booking.model.BookingModel;
import com.cg.railwayreservation.booking.proxy.LoginProxy;
import com.cg.railwayreservation.booking.proxy.PaymentProxy;
import com.cg.railwayreservation.booking.proxy.TrainProxy;
import com.cg.railwayreservation.booking.repository.BookingRepository;
import com.cg.railwayreservation.booking.serviceimpl.BookingServiceImpl;
import com.cg.railwayreservation.booking.vo.LoginModel;
import com.cg.railwayreservation.booking.vo.TrainBookingVo;
import com.cg.railwayreservation.booking.vo.TrainModel;

@SpringBootTest
public class BookingServiceTest {

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private TrainProxy trainProxy;

    @Mock
    private LoginProxy loginProxy;
    
    @Mock
    private PaymentProxy paymentProxy;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test

    void testBookTicket() {
        BookingModel booking = new BookingModel();
        booking.setTrainNo("123");
        booking.setNumberOfTickets(2);
        booking.setUsername("testUser");

        TrainModel train = new TrainModel();
        train.setTrainNo("123");
        train.setSeats(10);
        train.setFare(50);

        when(trainProxy.getTrainByNo("123")).thenReturn(train);   
        String result = bookingService.bookTicket(booking);
        verify(bookingRepository, times(1)).save(booking);
        verify(paymentProxy, times(1)).doPayment("testUser", booking.getPnr(), 100); // Assuming fare is 50 and numberOfTickets is 2

    }
    @Test
    public void testCancelTicket() {
        // Mock data
        String pnr = "PNR123";
        BookingModel booking = new BookingModel();
        booking.setPnr(pnr);
        booking.setTrainNo("12345");
        booking.setNumberOfTickets(2);

        when(bookingRepository.findByPnr(pnr)).thenReturn(booking);
        when(bookingRepository.deleteByPnr(pnr)).thenReturn("Successfully Cancel the Ticket");

        when(trainProxy.getTrainByNo("12345")).thenReturn(new TrainModel("12345", "Train 1", "City A", "City B", 100, 50, "10:00 AM"));

        String result = bookingService.cancelTicket(pnr);

        assertEquals("Successfully Cancel the Ticket", result);
    }

    @Test
    public void testGetAllBookings() {
        // Mock data
        BookingModel booking1 = new BookingModel();
        booking1.setPnr("PNR123");
        BookingModel booking2 = new BookingModel();
        booking2.setPnr("PNR456");
        when(bookingRepository.findAll()).thenReturn(List.of(booking1, booking2));

        // Perform the test
        List<BookingModel> result = bookingService.getAllBookings();

        // Verify the result
        assertEquals(2, result.size());
    }

    @Test
    public void testGetBookingByPNR() {
        String pnr = "PNR123";
        BookingModel booking = new BookingModel();
        booking.setPnr(pnr);
        when(bookingRepository.findByPnr(pnr)).thenReturn(booking);

        BookingModel result = bookingService.getBookingByPNR(pnr);

        assertEquals(pnr, result.getPnr());
    }

    @Test
    public void testGetBookingByUsername() {
        String username = "user1";

        BookingModel booking1 = new BookingModel();
        booking1.setUsername(username);
        BookingModel booking2 = new BookingModel();
        booking2.setUsername(username);

        List<BookingModel> allBookings = new ArrayList<>();
        allBookings.add(booking1);
        allBookings.add(booking2);
        when(bookingRepository.findAll()).thenReturn(allBookings);

        List<BookingModel> result = bookingService.getBookingByUsername(username);

        // Assertions
        assertEquals(2, result.size()); 
        assertTrue(result.contains(booking1)); 
        assertTrue(result.contains(booking2)); 
    }

    @Test
    public void testGetTicketDetailsWithTrainDetails() {
        String pnr = "PNR123";

        BookingModel booking = new BookingModel();
        booking.setPnr(pnr);
        booking.setUsername("user1");
        booking.setTrainNo("12345");

        TrainModel train = new TrainModel("12345", "Testtrain", "City A", "City B", 100, 50, "10:00 AM");

        LoginModel login = new LoginModel("user1", "user", "User", "user@gmail.com", "Male", 23, "India");

        when(bookingRepository.findByPnr(pnr)).thenReturn(booking);
        when(trainProxy.getTrainByNo("12345")).thenReturn(train);
        when(loginProxy.getbyUserName("user1")).thenReturn(login);

        TrainBookingVo result = bookingService.getTicketDetailsWithTrainDetaisl(pnr);

        assertEquals(train.getTrainName(), result.getTrainModel().getTrainName());
        assertEquals(booking.getUsername(), result.getLoginModel().getUsername());
    }
}
