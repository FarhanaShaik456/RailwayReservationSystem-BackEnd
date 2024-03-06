package com.cg.railwayreservation.admin.vo;


import java.util.List;

import com.cg.railwayreservation.admin.model.LoginModel;

import lombok.Data;

@Data
public class UserIssueVO {

	private LoginModel loginModel;
	private List<HelpModel> helpModel;
}
