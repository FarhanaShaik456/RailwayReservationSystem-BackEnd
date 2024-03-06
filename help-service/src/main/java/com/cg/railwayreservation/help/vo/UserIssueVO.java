package com.cg.railwayreservation.help.vo;

import java.util.List;

import com.cg.railwayreservation.help.model.HelpModel;

import lombok.Data;

@Data
public class UserIssueVO {

	private LoginModel loginModel;
	private List<HelpModel> helpModel;
}
