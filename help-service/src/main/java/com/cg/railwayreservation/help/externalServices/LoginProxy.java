package com.cg.railwayreservation.help.externalServices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.railwayreservation.help.vo.LoginModel;



@FeignClient(name = "LOGINSERVICE")
public interface LoginProxy {
	
	@GetMapping("/registration/autherization/getbyUsername/{username}")
	public LoginModel getbyUserName(@PathVariable String username);

}
