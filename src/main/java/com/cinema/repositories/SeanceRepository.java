package com.cinema.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.cinema.models.Seance;

public interface SeanceRepository extends MongoRepository<Seance, String> {

}
