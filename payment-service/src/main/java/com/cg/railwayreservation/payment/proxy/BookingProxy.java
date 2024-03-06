package com.cg.railwayreservation.payment.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.railwayreservation.payment.VO.BookingModel;

@FeignClient(name = "BOOKINGSERVICE", url="http://localhost:9007/bookings")
public interface BookingProxy {

	@GetMapping("/ViewTicketByUserName/{username}")
    public BookingModel viewByUserName(@PathVariable String username);
}
