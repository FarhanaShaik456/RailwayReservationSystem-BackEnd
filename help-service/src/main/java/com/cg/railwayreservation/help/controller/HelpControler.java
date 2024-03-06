package com.cg.railwayreservation.help.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.railwayreservation.help.model.HelpModel;
import com.cg.railwayreservation.help.serviceImpl.HelpServiceImpl;
import com.cg.railwayreservation.help.vo.UserIssueVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@RestController
@RequestMapping("/issue")
public class HelpControler {
	
	@Autowired
	private HelpServiceImpl helpServiceImpl;
	
	@PostMapping("/addIssue")
    public ResponseEntity<String> addissue(@RequestBody HelpModel helpModel) {
		log.info("Inside the addissue method in HelpController Class");
		helpServiceImpl.addissue(helpModel);
		return ResponseEntity.ok("Apologies for hearing the Issue. Our Admin will look into these");
	}
	
	@GetMapping("/getAllIssues")
	public ResponseEntity<List<HelpModel>> getAllIssues(){
		log.info("Inside the getAllIssues method in HelpController Class");
		return ResponseEntity.ok(helpServiceImpl.getAllIssues());
	}
	
	@GetMapping("/getByUsername/{username}")
	public ResponseEntity<UserIssueVO> getByUsername(@PathVariable String username) {
		log.info("Inside the getByUsername method in HelpController Class");
		return ResponseEntity.ok(helpServiceImpl.getByUsername(username));
	}
	
	@PutMapping("/update/{issue}")
	public ResponseEntity<HelpModel> updateIssue(@RequestBody HelpModel helpModel, @PathVariable String issue) {
		log.info("Inside the updateIssue method in HelpController Class");
		return ResponseEntity.ok(helpServiceImpl.updateIssue(helpModel, issue));
	}
	
	@GetMapping("/getUserissues/{username}")
	public List<HelpModel> getUserissues(@PathVariable String username) {
		return helpServiceImpl.getUserissues(username);
	}

}
