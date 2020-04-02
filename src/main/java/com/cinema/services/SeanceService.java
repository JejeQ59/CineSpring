package com.cinema.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.cinema.models.Film;
import com.cinema.models.Seance;

public interface SeanceService {

	public Seance save(Seance s);
	public List<Seance> findAll();
	public Optional<Seance> findById(String id);
	public Seance update(Seance s);
	public void delete(Seance s);
	public Seance addClient(String sid, String cid);
	public Seance addFilm(String sId, String fId);
	public Seance addSalle(String sId, String saId);
	public List<Seance> findBySeanceByTitreFilm(String titre);
	public float findRecetteSeance(String id);
	public int findPlacesRestantesSeance(String id);
	public List<Seance> findByDateBetween(LocalDateTime min, LocalDateTime max);
	public float recetteFilm(Film f);
	public void deleteById(String id);
	public List<Seance> findAllByFilmGenre(String genre);
	
}
