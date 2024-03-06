package com.cg.railwayreservation.payment.VO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingModel {

    private String pnr; 
    private String username;
    private String trainNo;
	private String phnnumber;
	private String email;
    private int numberOfTickets;
    private int Cost;
}
