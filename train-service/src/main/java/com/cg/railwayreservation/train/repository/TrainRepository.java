package com.cg.railwayreservation.train.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cg.railwayreservation.train.model.TrainModel;

@Repository
public interface TrainRepository extends MongoRepository<TrainModel, String>{

	List<TrainModel> findByTrainName(String trainName);
	List<TrainModel> findByTrainFromAndTrainTo(String trainFrom, String trainTo);
	List<TrainModel> findByTrainFrom(String trainFrom);
}
