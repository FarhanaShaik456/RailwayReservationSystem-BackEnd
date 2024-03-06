package com.cg.railwayreservation.help.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "issueResolve")
public class HelpModel {

	@Id
	private String issue;
	private String username;
	private String status;
	private String solution;
	

}
