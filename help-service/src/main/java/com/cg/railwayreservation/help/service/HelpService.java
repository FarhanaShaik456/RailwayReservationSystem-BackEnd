package com.cg.railwayreservation.help.service;

import java.util.List;

import com.cg.railwayreservation.help.model.HelpModel;
import com.cg.railwayreservation.help.vo.UserIssueVO;

public interface HelpService {
	
	public String addissue(HelpModel helpModel);
	
	public List<HelpModel> getAllIssues();
	
	public UserIssueVO getByUsername(String username);
	
	public HelpModel updateIssue(HelpModel helpModel, String username);

	List<HelpModel> getUserissues(String username);

}
