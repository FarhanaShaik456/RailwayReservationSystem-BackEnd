package com.cg.railwayreservation.booking.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.railwayreservation.booking.vo.LoginModel;


@FeignClient(name = "LOGINSERVICE", url="http://localhost:9005")
public interface LoginProxy {
	
	@GetMapping("/registration/autherization/getbyUsername/{username}")
	public LoginModel getbyUserName(@PathVariable String username);

}
