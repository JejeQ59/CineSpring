package com.cinema.services;

import java.util.List;
import java.util.Optional;

import com.cinema.models.Film;

public interface FilmService {

	public Film save(Film entity);
	public List<Film> findAll();
	public Optional<Film> findById(String id);
	public Film update(Film f);
	public void delete(Film f);
	public List<Film> findByTitreLike(String titre);
	public float findRecette(String id);
	public void deleteById(String id);
	public List<Film> findAllByGenre(String genre);
	public List<Film> findAllByAgeLimite(int age);
	public float findMoyenne(String id);
	
}
