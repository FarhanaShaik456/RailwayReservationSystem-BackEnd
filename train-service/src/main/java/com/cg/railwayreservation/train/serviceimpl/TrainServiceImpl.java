package com.cg.railwayreservation.train.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.railwayreservation.train.exception.TrainNotFoundException;
import com.cg.railwayreservation.train.model.TrainModel;
import com.cg.railwayreservation.train.repository.TrainRepository;
import com.cg.railwayreservation.train.service.TrainService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TrainServiceImpl implements TrainService{

	// Autowired TrainRepository for database operations
	@Autowired
	private TrainRepository trainRepository;

    // Method to add a new train model 
	@Override
	public TrainModel addTrainModel(TrainModel train) throws TrainNotFoundException{
		log.info("Add Method Started");
		// Check if required fields are empty, and throw an exception if they are
		    if (train.getTrainNo().isEmpty() ||
		        train.getTrainName().isEmpty() ||
		        train.getTrainFrom().isEmpty() ||
		        train.getTrainTo().isEmpty() ||
		        train.getTime().isEmpty() ||
		        train.getSeats() == 0 ||
		        train.getFare() == 0) {
		    	log.info("Inside the if condition of Add method");
		        throw new TrainNotFoundException("Please fill every field appropriately");
		   }
		    else {
		    log.info("Inside the else condition of Add method");
		    // Save the train model to the database
		    return trainRepository.save(train);
		    }

	}

	// Method to retrieve all trains 
	@Override
	public List<TrainModel> getAllTrains() throws TrainNotFoundException {
		log.info("Get All Trains Method Started");
		// Get all train models from the database
		List<TrainModel> findAll = trainRepository.findAll();
		if(!findAll.isEmpty()) {
			log.info("Inside the if condition of getAllTrains method");
			return findAll;
		}
		else {
			log.warn("Inside the else condition of getAllTrain method");
			throw new TrainNotFoundException("No data is found");
		}
	}

	// Method to get a train by its ID
	@Override
	public TrainModel getTrainById(String trainNo) throws TrainNotFoundException {
	    Optional<TrainModel> optionalTrainModel = trainRepository.findById(trainNo);
	    log.info("Get Train By Id Method Started");

	    if (optionalTrainModel.isPresent()) {
	        log.info("Inside the if condition of getTrainById method");
	        return optionalTrainModel.get();
	    } else {
	        log.info("Inside the else condition of getTrainById method");
	        throw new TrainNotFoundException("Train with ID " + trainNo + " not found");
	    }
	}

	// Method to delete a train by its ID
	@Override
	public String deleteTrain(String trainNo) throws TrainNotFoundException {
		log.info("Delete Train Method Started");
		Optional<TrainModel> optionalTrainModel = trainRepository.findById(trainNo);
		
		if (optionalTrainModel.isPresent()) {
		trainRepository.deleteById(trainNo);
		log.info("Inside the if condition of Delete method");
		return "Train is deleted Successfully";
		}
		else {
			throw new TrainNotFoundException("Train is not exists with the Train Id"+trainNo);
		}
	}

	
	// Method to get trains by name
	@Override
	public List<TrainModel> getTrainsbyname(String trainName) throws TrainNotFoundException {
		log.info("Get Train By Name Method is started");
		List<TrainModel> trainModels = trainRepository.findByTrainName(trainName);
		if(trainModels.isEmpty()) {
			log.warn("Inside the if condition of getTrainsbyname method");
			throw new TrainNotFoundException("No data is found by these "+trainName);
		}
		else {
			log.info("Inside the else condition of getTrainsbyname method");
			return trainModels;
		}
	}

	// Method to find trains by location
	@Override
	public List<TrainModel> findByLocation(String trainFrom, String trainTo)throws TrainNotFoundException {
		log.info("find By Location Method is started ");
		System.out.println("******************************"+trainFrom+""+trainTo);
		List<TrainModel> trainModels = trainRepository.findByTrainFromAndTrainTo(trainFrom, trainTo);
		System.out.println("******************************Error is here"+trainRepository.findByTrainFromAndTrainTo(trainFrom, trainTo));
		if(trainModels.isEmpty()) {
			log.info("Inside the if condition of findByLocation method");
			throw new TrainNotFoundException("No data is found for these destination");
		}
		else {
			log.warn("Inside the else condition of findByLocation method");
			return trainModels;
		}
	}

	// Method to update a train
	@Override
	public TrainModel updateTrain(String trainNo, TrainModel trainModel)throws TrainNotFoundException {
		log.info("Update Train method started");
		 Optional<TrainModel> findById = trainRepository.findById(trainNo);
		if(findById.isPresent()) {
			log.info("Inside the if condition of updateTrain method");
			 TrainModel train = findById.get();
			 train.setTrainName(trainModel.getTrainName());
			 train.setTrainFrom(trainModel.getTrainFrom());
			 train.setTrainTo(trainModel.getTrainTo());
			 train.setSeats(trainModel.getSeats());
			 train.setFare(trainModel.getFare());
			 train.setTime(trainModel.getTime());
			 TrainModel updated = trainRepository.save(train);
			 log.info("Train updated Successfully");
			return updated;
		}
		else {
			log.info("Inside the else condition of updateTrain method");
			throw new TrainNotFoundException("It doesn't exists for modification");
		}
	}

	

	
	
}
