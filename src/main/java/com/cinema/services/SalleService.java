package com.cinema.services;

import java.util.List;
import java.util.Optional;

import com.cinema.models.Salle;

public interface SalleService {

	public Salle save(Salle s);
	public List<Salle> findAll();
	public Optional<Salle> findById(String id);
	public Salle update(Salle f);
	public void delete(String id);

}
