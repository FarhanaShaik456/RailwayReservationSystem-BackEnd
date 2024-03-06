package com.cg.railwayreservation.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.railwayreservation.booking.model.BookingModel;
import com.cg.railwayreservation.booking.service.BookingService;
import com.cg.railwayreservation.booking.vo.TrainBookingVo;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/bookings")
@CrossOrigin(origins = "http://localhost:4200",methods = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST})
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/booking")
	public ResponseEntity<String> bookTicket(@Valid @RequestBody BookingModel booking) {
		log.info("Inside the bookTicket method of Controller");
		log.info("Booking A Ticket "+booking);
		return ResponseEntity.ok(bookingService.bookTicket(booking));
	}
	
	 @DeleteMapping("/cancelingTicketByPnr/{pnr}")
	    public ResponseEntity<String> cancelTicket(@PathVariable String pnr) {
		 log.info("Inside the cancelTicket method of Controller");
		 log.info("cancelling A Ticket successlly ");
		 return ResponseEntity.ok(bookingService.cancelTicket(pnr));
	 }
	 
	 @GetMapping("/ViewAllBookings")
	    public ResponseEntity<List<BookingModel>> viewAllBookings() {
		 log.info("Inside the addTrainModel method of Controller");
		 log.info("Retrieving AllBookings Data ");
		 return ResponseEntity.ok(bookingService.getAllBookings());
	 }
	 
	 @GetMapping("/ViewTicketByPnr/{pnr}") 
	    public ResponseEntity<BookingModel> viewByPnr(@PathVariable String pnr) {
		 log.info("Inside the addTrainModel method of Controller");
		 log.info("Retrieving Tickets Data by PNR ");
		 return ResponseEntity.ok(bookingService.getBookingByPNR(pnr));
	 }
	 
	 @GetMapping("/viewByUserName/{username}")
	    public ResponseEntity<List<BookingModel>> viewByUserName(@PathVariable String username) {
	        log.info("viewByUserName Method Started Inside the Authorize Controller");
	        List<BookingModel> bookings = bookingService.getBookingByUsername(username);
	        return ResponseEntity.ok(bookings);
	    }
	 
	 @GetMapping("/ViewBookingTicketByItsTrainAndTotalCost/{pnr}")
	 public TrainBookingVo getBookingTicketByItsTrainAndTotalCost(@PathVariable String pnr) {
		 return bookingService.getTicketDetailsWithTrainDetaisl(pnr);
	 }
}
