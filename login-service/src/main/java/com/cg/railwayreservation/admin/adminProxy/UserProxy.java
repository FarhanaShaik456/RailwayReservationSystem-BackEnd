package com.cg.railwayreservation.admin.adminProxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cg.railwayreservation.admin.vo.UserModel;



@FeignClient(name = "USERSERVICE", url="http://localhost:9006/user/access")
public interface UserProxy {

	@GetMapping("/getAllUsers")
	public List<UserModel> getAllUsers();
	
	@GetMapping("/getbyUsername/{username}")
	public UserModel getbyUserName(@PathVariable String username);
}
