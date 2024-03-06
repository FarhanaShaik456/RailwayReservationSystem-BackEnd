package com.cg.railwayreservation.admin.vo;


import com.cg.railwayreservation.admin.model.LoginModel;

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
