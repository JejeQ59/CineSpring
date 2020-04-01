package com.cinema.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cinema.models.Assister;

public interface AssisterRepository extends MongoRepository<Assister, String> {

}
