package com.cg.railwayreservation.train.Repository;
import com.cg.railwayreservation.train.model.TrainModel;
import com.cg.railwayreservation.train.repository.TrainRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TrainRepositoryTest {

    @Mock
    private TrainRepository trainRepository;

 // Test method for finding all trains
    @Test
    public void testFindAll() {
        // Mock data
        TrainModel train1 = new TrainModel("12345", "Intercity", "Warangal", "Hyderabad", 100, 50, "7:45 AM");
        TrainModel train2 = new TrainModel("67890", "shatavana", "Secundrabad", "kazipet", 150, 40, "4:35 PM");
        List<TrainModel> trains = new ArrayList<>();
        trains.add(train1);
        trains.add(train2);

        when(trainRepository.findAll()).thenReturn(trains);

        List<TrainModel> result = trainRepository.findAll();
        assertEquals(2, result.size());
    }

 // Test method for finding a train by ID
    @Test
    public void testFindById() {
        TrainModel train = new TrainModel("12345", "Intercity", "warangal", "Hyderabad", 100, 50, "7:45 AM");

        when(trainRepository.findById("12345")).thenReturn(Optional.of(train));

        Optional<TrainModel> result = trainRepository.findById("12345");

        assertEquals(true, result.isPresent());
        assertEquals("Intercity", result.get().getTrainName());
    }

 // Test method for finding trains by train name
    @Test
    public void testFindByTrainName() {
       
        TrainModel train1 = new TrainModel("12345", "SuperFastExpress", "tirupathi", "Sec-jun", 450, 50, "7:45 AM");
        TrainModel train2 = new TrainModel("67890", "CharminarExpress", "Nampally", "kazipet", 150, 40, "3:30 PM");
        List<TrainModel> trains = new ArrayList<>();
        trains.add(train1);
        trains.add(train2);

        when(trainRepository.findByTrainName("CharminarExpress")).thenReturn(trains);

        List<TrainModel> result = trainRepository.findByTrainName("CharminarExpress");

        // Verify the result
        assertEquals(2,result.size());
        assertEquals("CharminarExpress", result.get(1).getTrainName());
    }
    
   // Test method for finding trains by both source and destination
    @Test
    public void testFindByTrainFromAndTrainTo() {
        // Mock data
        TrainModel train1 = new TrainModel("12345", "Train 1", "City A", "City B", 100, 50, "10:00 AM");
        TrainModel train2 = new TrainModel("67890", "Train 2", "City B", "City C", 150, 40, "12:00 PM");
       
        List<TrainModel> trains = new ArrayList<>();
        trains.add(train1);
        trains.add(train2);

        when(trainRepository.findByTrainFromAndTrainTo("City A", "City B")).thenReturn(trains);

        List<TrainModel> result = trainRepository.findByTrainFromAndTrainTo("City A", "City B");

        assertEquals(2, result.size());
        assertEquals("City A", result.get(0).getTrainFrom());
        assertEquals("City B", result.get(0).getTrainTo());
    }


}
