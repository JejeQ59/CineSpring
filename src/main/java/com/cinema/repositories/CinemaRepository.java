package com.cinema.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.cinema.models.Cinema;

public interface CinemaRepository extends MongoRepository<Cinema, String> {

}
