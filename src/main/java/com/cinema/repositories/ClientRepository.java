package com.cinema.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.cinema.models.Client;

public interface ClientRepository extends MongoRepository<Client, String> {

}
