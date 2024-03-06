package com.cg.railwayreservation.booking.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.railwayreservation.booking.model.BookingModel;

@SpringBootTest
public class BookingRepositoyTest {

	
	 private BookingRepository bookingRepository;

	    @BeforeEach
	    public void setUp() {
	        bookingRepository = mock(BookingRepository.class);
	    }

	    @Test
	    public void testFindByPnr() {
	        String pnr = "ABC123";
	        BookingModel expectedBooking = new BookingModel();
	        expectedBooking.setPnr(pnr); 

	        when(bookingRepository.findByPnr(pnr)).thenReturn(expectedBooking);

	        BookingModel result = bookingRepository.findByPnr(pnr);

	        assertNotNull(result);
	        assertEquals(pnr, result.getPnr());
	    }
	 
	    
	    @Test
	    public void testDeleteByPnr() {
	        String pnr = "ABC123";

	        String expectedMessage = "Deleted 1 booking(s) with PNR: " + pnr;
	        when(bookingRepository.deleteByPnr(pnr)).thenReturn(expectedMessage);

	        String result = bookingRepository.deleteByPnr(pnr);

	        assertNotNull(result);
	        assertEquals(expectedMessage, result);
	    }
	    
	    @Test
	    public void testFindByUsername() {
	        String username = "user1";
	        BookingModel expectedBooking = new BookingModel();
	        expectedBooking.setUsername(username); // Set other necessary properties

	        when(bookingRepository.findByUsername(username)).thenReturn(expectedBooking);

	        BookingModel result = bookingRepository.findByUsername(username);

	        assertNotNull(result);
	        assertEquals(username, result.getUsername());
	    }
}
