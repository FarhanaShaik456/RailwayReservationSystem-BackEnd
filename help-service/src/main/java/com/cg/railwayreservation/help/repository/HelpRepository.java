package com.cg.railwayreservation.help.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cg.railwayreservation.help.model.HelpModel;

@Repository
public interface HelpRepository extends MongoRepository<HelpModel, String> {

	HelpModel findByUsername(String username);

	HelpModel findByIssue(String issue);

}
