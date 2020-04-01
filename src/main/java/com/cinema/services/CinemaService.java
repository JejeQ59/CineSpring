package com.cinema.services;

import java.util.List;
import java.util.Optional;

import com.cinema.models.Cinema;

public interface CinemaService {

	public Cinema save(Cinema c);
	public List<Cinema> findAll();
	public Optional<Cinema> findById(String id);
	public Cinema update(Cinema c);
	public void delete(String id);
	
}
