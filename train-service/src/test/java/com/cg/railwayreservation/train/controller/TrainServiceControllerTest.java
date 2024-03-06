package com.cg.railwayreservation.train.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.cg.railwayreservation.train.model.TrainModel;
import com.cg.railwayreservation.train.service.TrainService;

@SpringBootTest
public class TrainServiceControllerTest {

    @Autowired
    private TrainController traincontroller;

    @MockBean
    private TrainService trainservice;

    // Test for adding a TrainModel
    @Test
    public void addTrainModel_test() {
        // Create a sample TrainModel
        TrainModel train = new TrainModel("1", "Express Train", "Nandyal", "Banglore", 67, 67, "10:00 AM");
        
        // Mock the behavior of the TrainService to return the same TrainModel
        when(trainservice.addTrainModel(train)).thenReturn(train);
        
        // Call the addTrainModel method in the controller
        ResponseEntity<TrainModel> response = traincontroller.addTrainModel(train);
        
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        
        assertEquals(train, response.getBody());
    }

    // Test for retrieving all Trains
    @Test
    public void getAllTrains_test() {
        // Create a list of sample TrainModels
        List<TrainModel> trainList = new ArrayList<>();
        trainList.add(new TrainModel("1", "Express Train", "Nandyal", "Banglore", 67, 67, "10:00 AM"));
        trainList.add(new TrainModel("2", "Kachiguda", "Banglore", "Nandyak", 67, 67, "10:00 AM"));

        // Mock the behavior of the TrainService to return the list of TrainModels
        when(trainservice.getAllTrains()).thenReturn(trainList);
        
        // Call the getAllTrains method in the controller
        ResponseEntity<List<TrainModel>> response = traincontroller.getAllTrains();
        
        // Get the list of TrainModels from the response
        List<TrainModel> trains = response.getBody();
        
        // Assert that the response contains 2 TrainModels
        assertEquals(2, trains.size());
    }

    // Test for retrieving a TrainModel by ID
    @Test
    public void getTrainById_test() {
        // Create a sample TrainModel and specify its ID
        TrainModel t = new TrainModel("1", "Express Train", "Nandyal", "Banglore", 67, 67, "10:00 AM");
        
        // Mock the behavior of the TrainService to return the same TrainModel when given the specified ID
        when(trainservice.getTrainById("1")).thenReturn(t);
        
        // Call the getTrainById method in the controller
        ResponseEntity<TrainModel> response = traincontroller.getTrainById("1");
        
        // Get the TrainModel from the response
        TrainModel train = response.getBody();
        
        // Assert that the response contains the expected TrainModel
        assertEquals(t, train);
    }

    // Test for retrieving Trains by name
    @Test
    public void getTrainByName_test() {
        // Create a list of sample TrainModels
        List<TrainModel> trainList = new ArrayList<>();
        trainList.add(new TrainModel("1", "Intercity", "Warangal", "Hyderabad", 80, 30, "7:45 AM"));
        trainList.add(new TrainModel("2", "shatavana", "warangal", "Secundrabad", 105, 90, "12:30 PM"));

        // Filter the list to only include TrainModels with the name "Intercity"
        List<TrainModel> filteredList = new ArrayList<>();
        for (TrainModel train : trainList) {
            if ("Intercity".equals(train.getTrainName())) {
                filteredList.add(train);
            }
        }

        // Mock the behavior of the TrainService to return the filtered list when given the name "Intercity"
        when(trainservice.getTrainsbyname("Intercity")).thenReturn(filteredList);

        ResponseEntity<List<TrainModel>> response = traincontroller.getTrainsbyname("Intercity");

        assertNotNull(response);
        
        List<TrainModel> trains = response.getBody();
        
        assertEquals(1, trains.size());
        assertEquals("Intercity", trains.get(0).getTrainName());
    }

    // Test for deleting a Train
    @Test
    public void deleteTrain_test() {
        // Specify a train number for deletion
        String trainNo = "123";

        String expectedResponse = "Train with number " + trainNo + " has been deleted successfully";

        // Mock the behavior of the TrainService to return the expected response message
        when(trainservice.deleteTrain(trainNo)).thenReturn(expectedResponse);

        // Call the deleteTrain method in the controller
        ResponseEntity<String> response = traincontroller.deleteTrain(trainNo);

        assertNotNull(response);

        assertEquals(expectedResponse, response.getBody());
    }

    // Test for finding Trains by location
    @Test
    public void findByLocation_test() {
        // Specify the source and destination locations
        String trainFrom = "Warangal";
        String trainTo = "Hyderabad";

        // Create a list of sample TrainModels
        List<TrainModel> trainList = new ArrayList<>();
        trainList.add(new TrainModel("1", "Intercity", "Warangal", "Hyderabad", 80, 30, "7:45 AM"));
        trainList.add(new TrainModel("2", "shatavana", "warangal", "Secundrabad", 105, 90, "12:30 PM"));

        List<TrainModel> filteredList = new ArrayList<>();
        for (TrainModel train : trainList) {
            if (trainFrom.equals(train.getTrainFrom()) && trainTo.equals(train.getTrainTo())) {
                filteredList.add(train);
            }
        }

        when(trainservice.findByLocation(trainFrom, trainTo)).thenReturn(filteredList);

        ResponseEntity<List<TrainModel>> response = traincontroller.findByLocation(trainFrom, trainTo);

        assertNotNull(response);

        assertEquals(200, response.getStatusCodeValue());

        List<TrainModel> trains = response.getBody();
        
        assertNotNull(trains);
        assertEquals(1, trains.size());
    }

    // Test for updating a Train
    @Test
    public void updateTrain_test() {
        // Specify a train number for updating
        String trainNo = "123";

        // Create an updated TrainModel
        TrainModel updatedTrain = new TrainModel(trainNo, "UpdatedTrain", "Warangal", "Hyderabad", 80, 30, "7:45 AM");

        // Mock the behavior of the TrainService to return the updated TrainModel
        when(trainservice.updateTrain(trainNo, updatedTrain)).thenReturn(updatedTrain);

        ResponseEntity<TrainModel> response = traincontroller.updateTrain(trainNo, updatedTrain);

        assertNotNull(response);

        assertEquals(200, response.getStatusCodeValue());

        assertEquals(updatedTrain, response.getBody());
    }
}
