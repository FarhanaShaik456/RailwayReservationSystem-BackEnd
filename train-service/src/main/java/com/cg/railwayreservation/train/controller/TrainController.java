package com.cg.railwayreservation.train.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.railwayreservation.train.exception.TrainNotFoundException;
import com.cg.railwayreservation.train.model.TrainModel;
import com.cg.railwayreservation.train.service.TrainService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/train")
public class TrainController {

	@Autowired
	TrainService trainService;


	// Add a new train
	@PostMapping("/addtrain")
	public ResponseEntity<TrainModel> addTrainModel(@RequestBody @Valid TrainModel train) throws TrainNotFoundException {
		log.info("Inside the addTrainModel method of Controller");
		log.info("Adding the Train Details "+train);
		return ResponseEntity.ok(trainService.addTrainModel(train));
	}
	
	// Get a list of all trains
	@GetMapping("/viewalltrains")
	public ResponseEntity<List<TrainModel>> getAllTrains()throws TrainNotFoundException {
		log.info("Inside the getAllTrains method of Controller");
		log.info("Retrieving Trains Data ");
		return ResponseEntity.ok(trainService.getAllTrains());
	}

	// Get a train by its trainNo
	@GetMapping("viewtrainbytrainNo/{trainNo}")
	public ResponseEntity<TrainModel> getTrainById(@PathVariable String trainNo)throws TrainNotFoundException {
		log.info("Inside the getTrainById method of Controller");
		log.info("Retrieving Train by TrainNo");
		return ResponseEntity.ok(trainService.getTrainById(trainNo));
	}

	// Delete a train by its trainNo
	@DeleteMapping("/deletetrain/{trainNo}")
	public ResponseEntity<String> deleteTrain(@PathVariable String trainNo) throws TrainNotFoundException {
		log.info("Inside the deleteTrain method of Controller");
		log.info("Delete Train by TrainNo");
		return ResponseEntity.ok(trainService.deleteTrain(trainNo));
	}

	// Find trains between two locations (trainFrom and trainTo)
	@GetMapping("/findbetween/{trainFrom}/{trainTo}")
	public ResponseEntity<List<TrainModel>> findByLocation(@PathVariable String trainFrom, @PathVariable String trainTo) throws TrainNotFoundException{
		log.info("Inside the findByLocation method of Controller");
		log.info("Retrieving Trains by Destination ");
		return ResponseEntity.ok(trainService.findByLocation(trainFrom,trainTo));
	}
	
	// Get trains by trainName
	@GetMapping("/viewtrainbyname/{trainName}")
	public ResponseEntity<List<TrainModel>> getTrainsbyname(@PathVariable("trainName") String trainName) throws TrainNotFoundException {
		log.info("Inside the getTrainsbyname method of Controller");
		log.info("Retrieving Trains by TrainName ");
		return ResponseEntity.ok(trainService.getTrainsbyname(trainName));		
	}
	
	// Update a train by its trainNo
	@PutMapping("updatetrainbyid/{trainNo}")
	public ResponseEntity<TrainModel> updateTrain(@PathVariable String trainNo,@RequestBody TrainModel trainModel) throws TrainNotFoundException {
		log.info("Existing data has been updated");
		return ResponseEntity.ok(trainService.updateTrain(trainNo, trainModel));
	}
	
}
