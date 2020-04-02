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
		return this.repo.findById(id);
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
	public Optional<Film> findByTitre(String titre) {
		return this.repo.findAllByTitre(titre);
	}
	
	@Override
	public List<Film> findAllByGenre(String genre) {
		return this.repo.findAllByGenre(genre);
	}

	@Override
	public float findRecette(String id) {
		float res=0;
		Optional<Film> film = this.repo.findById(id);
		if(film.isPresent())
		{
			res = this.serviceS.recetteFilm(film.get());
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "le film d'id: " + id + " n'existe pas");
		}
		
		return res;
	}

	@Override
	public List<Film> findAllByAgeLimite(int age) {
		return this.repo.findAllByAgeLimite(age);
	}
	
	

	
	
	

}
