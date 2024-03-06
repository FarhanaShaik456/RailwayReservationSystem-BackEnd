package com.cg.railwayreservation.train.service;

import java.util.List;

import com.cg.railwayreservation.train.exception.TrainNotFoundException;
import com.cg.railwayreservation.train.model.TrainModel;

public interface TrainService {

	public TrainModel addTrainModel (TrainModel train) throws TrainNotFoundException;
	public List<TrainModel> getAllTrains() throws TrainNotFoundException;
	public TrainModel getTrainById(String trainNo) throws TrainNotFoundException;
	public String deleteTrain(String trainNo) throws TrainNotFoundException;
	public List<TrainModel> findByLocation(String trainFrom,String trainTo) throws TrainNotFoundException;
	public List<TrainModel> getTrainsbyname(String trainName) throws TrainNotFoundException;
	public TrainModel updateTrain(String trainNo, TrainModel trainModel) throws TrainNotFoundException;
}
