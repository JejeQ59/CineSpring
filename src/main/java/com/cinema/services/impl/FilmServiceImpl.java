package com.cinema.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinema.models.Film;
import com.cinema.repositories.FilmRepository;
import com.cinema.services.FilmService;

@Service
public class FilmServiceImpl implements FilmService {

	@Autowired
	private FilmRepository repo;

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
	public void delete(String id) {
		this.repo.deleteById(id);
		
	}

	@Override
	public Optional<Film> findByTitre(String titre) {
		return this.repo.findAllByTitre(titre);
	}

	@Override
	public float findRecette(String id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

	
	
	

}
