package com.cinema.services;

import java.util.List;
import java.util.Optional;

import com.cinema.models.Film;

public interface FilmService {

	public Film save(Film entity);
	public List<Film> findAll();
	public Optional<Film> findById(String id);
	public Film update(Film f);
	public void delete(String id);
	public Optional<Film> findByTitre(String titre);

}
