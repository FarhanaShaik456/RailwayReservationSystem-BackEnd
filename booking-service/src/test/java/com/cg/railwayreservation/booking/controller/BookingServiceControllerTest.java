package com.cg.railwayreservation.booking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.cg.railwayreservation.booking.model.BookingModel;
import com.cg.railwayreservation.booking.service.BookingService;
import com.cg.railwayreservation.booking.vo.TrainBookingVo;
@SpringBootTest
public class BookingServiceControllerTest {
	

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;


	    @Test
	    public void testBookTicket() {
	        BookingModel booking = new BookingModel();
	        booking.setUsername("user1");
	        booking.setTrainNo("12345");
	        booking.setNumberOfTickets(2);

	        when(bookingService.bookTicket(booking)).thenReturn("Ticket Confirmed Happy Journey");

	        ResponseEntity<String> response = bookingController.bookTicket(booking);

	        assertEquals(200, response.getStatusCodeValue());
	        assertEquals("Ticket Confirmed Happy Journey", response.getBody());
	    }

	    @Test
	    public void testCancelTicket() {
	        String pnr = "PNR123";

	        when(bookingService.cancelTicket(pnr)).thenReturn("Successfully Cancel the Ticket");

	        ResponseEntity<String> response = bookingController.cancelTicket(pnr);

	        assertEquals(200, response.getStatusCodeValue());
	        assertEquals("Successfully Cancel the Ticket", response.getBody());
	        //verify(bookingService, times(1)).cancelTicket(pnr);
	    }

	    @Test
	    public void testViewAllBookings() {
	        List<BookingModel> bookings = new ArrayList<>();
	        
	        BookingModel booking1 = new BookingModel();
	        booking1.setPnr("PNR1");
	        bookings.add(booking1);

	        BookingModel booking2 = new BookingModel();
	        booking2.setPnr("PNR2");
	        bookings.add(booking2);
	        when(bookingService.getAllBookings()).thenReturn(bookings);

	        ResponseEntity<List<BookingModel>> response = bookingController.viewAllBookings();

	        assertEquals(200, response.getStatusCodeValue());
	        assertEquals(bookings, response.getBody());
	        assertEquals(2,response.getBody().size());
	    }


	    @Test
	    public void testViewByPnr() {
	        String pnr = "PNR123";
	        BookingModel booking = new BookingModel();
	        booking.setPnr(pnr);

	        when(bookingService.getBookingByPNR(pnr)).thenReturn(booking);

	        ResponseEntity<BookingModel> response = bookingController.viewByPnr(pnr);

	        assertEquals(200, response.getStatusCodeValue());
	        assertEquals(booking, response.getBody());
	      //  verify(bookingService, times(1)).getBookingByPNR(pnr);
	    }

	  

	    @Test
	    public void testGetTicketDetailsWithTrainDetails() {
	        String pnr = "PNR123";
	        TrainBookingVo trainBookingVo = new TrainBookingVo();
	        BookingModel booking = new BookingModel();
	        booking.setPnr(pnr);

	        when(bookingService.getTicketDetailsWithTrainDetaisl(pnr)).thenReturn(trainBookingVo);

	        TrainBookingVo result = bookingController.getBookingTicketByItsTrainAndTotalCost(pnr);

	        assertEquals(trainBookingVo, result);
	    }
	    
	    @Test
	    public void testViewByUserName() {
	   
	        String username = "user123";
	        List<BookingModel> mockBookings = new ArrayList<>();
	        // Add some sample bookings to the list
	        BookingModel booking1 = new BookingModel();
	        booking1.setUsername(username);
	        
	        mockBookings.add(booking1);
	       
	        
	        when(bookingService.getBookingByUsername(username)).thenReturn(mockBookings);

	 
	        ResponseEntity<List<BookingModel>> responseEntity = bookingController.viewByUserName(username);

	        assertEquals(200, responseEntity.getStatusCodeValue()); 
//	      
	    }
}
