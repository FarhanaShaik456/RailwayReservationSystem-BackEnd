package com.cg.railwayreservation.booking.proxy;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.railwayreservation.booking.vo.TrainModel;


@FeignClient(name = "TRAINSERVICE", url="http://localhost:9004/train")
public interface TrainProxy {
 	
	@GetMapping(value = "/viewtrainbytrainNo/{trainNo}")
	public TrainModel getTrainByNo(@PathVariable String trainNo);
	
	@PutMapping("updatetrainbyid/{trainNo}")
	public TrainModel updateTrain(@PathVariable String trainNo,@RequestBody TrainModel trainModel); 
	
	
}
