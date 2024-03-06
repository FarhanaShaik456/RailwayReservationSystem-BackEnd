package com.cg.railwayreservation.booking.service;

import java.util.List;

import com.cg.railwayreservation.booking.model.BookingModel;
import com.cg.railwayreservation.booking.vo.TrainBookingVo;

public interface BookingService {

	 public String bookTicket(BookingModel booking);
	 public String cancelTicket(String pnr);
	 public List<BookingModel> getAllBookings();
	 public BookingModel getBookingByPNR(String pnr);
	 public List<BookingModel> getBookingByUsername(String username);
	 public TrainBookingVo getTicketDetailsWithTrainDetaisl(String pnr);
}
