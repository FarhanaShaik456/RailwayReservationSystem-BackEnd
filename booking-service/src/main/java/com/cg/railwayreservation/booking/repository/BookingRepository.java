package com.cg.railwayreservation.booking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cg.railwayreservation.booking.model.BookingModel;

@Repository
public interface BookingRepository extends MongoRepository<BookingModel, String>{

	BookingModel findByPnr(String pnr);

	String deleteByPnr(String pnr);

	BookingModel findByUsername(String username);

}
