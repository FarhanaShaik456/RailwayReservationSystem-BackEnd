package com.cg.railwayreservation.booking.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainModel {

	 private String trainNo;
	 private String trainName;
	 private String trainFrom;
	 private String trainTo;
	 private int fare;
	 private int seats;
	 private String time;
	 
}
