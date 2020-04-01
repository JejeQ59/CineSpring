package com.cinema.services;

import java.util.List;
import java.util.Optional;

import com.cinema.models.Seance;

public interface SeanceService {

	public Seance save(Seance s);
	public List<Seance> findAll();
	public Optional<Seance> findById(String id);
	public Seance update(Seance s);
	public void delete(String id);
	public Seance addClient(String sid, String cid);
	public Seance addFilm(String sId, String fId);
	public Seance addSalle(String sId, String saId);
	
}