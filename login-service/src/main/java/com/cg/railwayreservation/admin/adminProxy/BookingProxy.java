package com.cg.railwayreservation.admin.adminProxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.railwayreservation.admin.vo.BookingModel;
import com.cg.railwayreservation.admin.vo.TrainBookingVo;

@FeignClient(name = "BOOKINGSERVICE", url = "http://localhost:9001/bookings")
public interface BookingProxy {

	@PostMapping("/booking")
	public String bookTicket(@RequestBody BookingModel booking);

	@DeleteMapping("/cancelingTicketByPnr/{pnr}")
	public String cancelTicket(@PathVariable String pnr);

	@GetMapping("/ViewTicketByPnr/{pnr}")
	public BookingModel viewByPnr(@PathVariable String pnr);

	@GetMapping("/viewByUserName/{username}")
	public List<BookingModel> viewByUserName(@PathVariable String username);

	@GetMapping("/ViewBookingTicketByItsTrainAndTotalCost/{pnr}")
	public TrainBookingVo getBookingTicketByItsTrainAndTotalCost(@PathVariable String pnr);

	@GetMapping("/ViewAllBookings")
	public List<BookingModel> viewAllBookings();

}
