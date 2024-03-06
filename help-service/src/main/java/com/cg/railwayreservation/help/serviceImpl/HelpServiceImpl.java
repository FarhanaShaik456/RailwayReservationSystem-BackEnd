package com.cg.railwayreservation.help.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.railwayreservation.help.exception.HelpException;
import com.cg.railwayreservation.help.externalServices.LoginProxy;
import com.cg.railwayreservation.help.model.HelpModel;
import com.cg.railwayreservation.help.repository.HelpRepository;
import com.cg.railwayreservation.help.service.HelpService;
import com.cg.railwayreservation.help.vo.LoginModel;
import com.cg.railwayreservation.help.vo.UserIssueVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HelpServiceImpl implements HelpService {

	@Autowired
	private HelpRepository helpRepository;

	@Autowired
	private LoginProxy loginProxy;

	@Override
	public String addissue(HelpModel helpModel) {
		log.info("AddIssue Method Started Inside the HelpServiceImpl Class");
		String issue = helpModel.getIssue();
		helpModel.setStatus("New");
		if (issue.isEmpty()) {
			log.info("Inside the if condition of AddIssueMethod in the HelpServiceImpl Class");
			throw new HelpException("Please provide the Issue.");
		} else {
			log.info("Inside the else condition of AddIssueMethod in the HelpServiceImpl Class");
			helpRepository.insert(helpModel);
			return "Apologies for hearing the Issue. Our Admin will look into these";
		}
	}

	@Override
	public List<HelpModel> getAllIssues() {
	    log.info("getAllIssues Method Started Inside the HelpServiceImpl Class");
	    List<HelpModel> issues = helpRepository.findAll();
	    
	    if (issues.isEmpty()) {
	        throw new HelpException("No issues found.");
	    }

	    return issues;
	}


	@Override
	public UserIssueVO getByUsername(String username) {
		List<HelpModel> list = new ArrayList<HelpModel>();
		List<HelpModel> all = helpRepository.findAll();
		System.out.println(all + "***************************");
		UserIssueVO userIssueVO = new UserIssueVO();

		for (HelpModel h : all) {
			String username2 = h.getUsername();
			System.out.println("******************************************************************");
			System.out.println("Error Here ****************" + username2);

			if (username2.equals(username)) {
				list.add(h);
			}
		}

		if (!list.isEmpty()) {
			LoginModel userDetails = loginProxy.getbyUserName(username);
			userIssueVO.setHelpModel(list); // Set the list of matching HelpModels
			userIssueVO.setLoginModel(userDetails);
		}

		return userIssueVO;
	}

	@Override
	public List<HelpModel> getUserissues(String username) {
		List<HelpModel> list = new ArrayList<HelpModel>();
		List<HelpModel> all = helpRepository.findAll();
		System.out.println(all + "***************************");

		for (HelpModel h : all) {
			String username2 = h.getUsername();
			System.out.println("******************************************************************");
			System.out.println("Error Here ****************" + username2);

			if (username2.equals(username)) {
				list.add(h);
			}
		}
		return list;
	}

	@Override
	public HelpModel updateIssue(HelpModel helpModel, String issue) {
	    HelpModel helpModel1 = helpRepository.findByIssue(issue);
	    log.info("update Issue Method is started");
	    log.info("**************************"+helpModel1);
	    log.info("**********************"+issue+"*************************************");
	    if (helpModel1 != null) {
	        String newStatus = helpModel.getStatus();
	        log.info("Inside the if condition of UpdateIssue Method HelpModel1");
	        if ("processing".equalsIgnoreCase(newStatus) || "resolved".equalsIgnoreCase(newStatus)) {
	            // Update the existing helpModel1 object instead of creating a new one
	            helpModel1.setStatus(newStatus);
	            helpModel1.setSolution(helpModel.getSolution());
	            helpRepository.save(helpModel1);
	        } else {
	            throw new HelpException("Give Proper Status");
	        }
	        return helpModel1;
	    } else {
	        throw new HelpException("There is nothing to update :)");
	    }
	}


}
