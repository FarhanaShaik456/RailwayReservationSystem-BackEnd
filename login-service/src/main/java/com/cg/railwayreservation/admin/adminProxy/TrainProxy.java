package com.cg.railwayreservation.admin.adminProxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.railwayreservation.admin.vo.TrainModel;


@FeignClient(name = "TRAINSERVICE", url="http://localhost:9004/train")
public interface TrainProxy {

	@GetMapping(value = "/viewalltrains")
	public List<TrainModel> getTrainModel();
	
	@PostMapping(value = "/addtrain")
	public String addtrain(@RequestBody TrainModel trainmodel);
	
	@GetMapping(value = "/viewtrainbytrainNo/{trainNo}")
	public TrainModel getTrainByNo(@PathVariable String trainNo);
	
	@GetMapping("/findbetween/{trainFrom}/{trainTo}")
	public List<TrainModel> findByLocation(@PathVariable String trainFrom, @PathVariable String trainTo);
	
	@GetMapping("/viewtrainbyname/{trainName}")
	public List<TrainModel> getTrainsbyname(@PathVariable("trainName") String trainName); 
	
	@PutMapping("updatetrainbyid/{trainNo}")
	public TrainModel updateTrain(@PathVariable String trainNo,@RequestBody TrainModel trainModel); 
	
	@DeleteMapping("/deletetrain/{trainNo}")
	public String deleteTrain(@PathVariable String trainNo); 
}
