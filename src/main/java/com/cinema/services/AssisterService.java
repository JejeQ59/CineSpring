package com.cinema.services;

import java.util.List;
import java.util.Optional;

import com.cinema.models.Assister;

public interface AssisterService {

	public Assister save(Assister a);
	public List<Assister> findAll();
	public Optional<Assister> findById(String id);
	public Assister update(Assister a);
	public void delete(String id);
	
}
