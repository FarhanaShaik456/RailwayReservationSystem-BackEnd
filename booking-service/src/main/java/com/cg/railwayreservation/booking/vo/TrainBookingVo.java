package com.cg.railwayreservation.booking.vo;

import com.cg.railwayreservation.booking.model.BookingModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TrainBookingVo {

	private TrainModel trainModel;
	private BookingModel bookingModel;
	private LoginModel loginModel;
}
