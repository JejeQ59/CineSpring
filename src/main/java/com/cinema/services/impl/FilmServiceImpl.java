package com.cinema.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cinema.models.Film;
import com.cinema.repositories.FilmRepository;
import com.cinema.services.FilmService;
import com.cinema.services.SeanceService;

@Service
public class FilmServiceImpl implements FilmService {

	@Autowired
	private FilmRepository repo;
	
	@Autowired
	private SeanceService serviceS;

	@Override
	public Film save(Film f) {
		return this.repo.save(f);
	}

	@Override
	public List<Film> findAll() {
		return this.repo.findAll();
	}

	@Override
	public Optional<Film> findById(String id) {
		if(this.repo.findById(id).isPresent()) {
			return this.repo.findById(id);
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "le film avec l'id " + id + " n'existe pas");
		}
	
	}

	@Override
	public Film update(Film f) {
		return this.repo.save(f);
	}

	@Override
	public void deleteById(String id) {
		this.repo.deleteById(id);
		
	}
	
	@Override
	public void delete(Film f) {
		this.repo.delete(f);
		
	}

	@Override
	public List<Film> findByTitreLike(String titre) {
		return this.repo.findAllByTitreLike(titre);
	}
	
	@Override
	public List<Film> findAllByGenre(String genre) {
		return this.repo.findAllByGenre(genre);
	}

	@Override
	public float findRecette(String id) {
		Optional<Film> film = this.repo.findById(id);
		return this.serviceS.recetteFilm(film.get());
	}

	@Override
	public List<Film> findAllByAgeLimite(int age) {
		return this.repo.findAllByAgeLimite(age);
	}

	@Override
	public float findMoyenne(String id) {
		Optional<Film> film = this.repo.findById(id);
		return this.serviceS.moyenneFilm(film.get());
	}
	
	

	
	
	

}
