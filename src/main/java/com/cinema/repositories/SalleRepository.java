package com.cinema.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.cinema.models.Salle;

public interface SalleRepository extends MongoRepository<Salle, String> {
	


}
