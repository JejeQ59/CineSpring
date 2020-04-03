package com.cinema.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cinema.models.Salle;
import com.cinema.repositories.SalleRepository;
import com.cinema.services.SalleService;

@Service
public class SalleServiceImpl implements SalleService {

	@Autowired
	private SalleRepository repo;
	
	@Override
	public Salle save(Salle s) {
		return this.repo.save(s);
	}
	
	@Override
	public List<Salle> findAll() {
		return this.repo.findAll();
	}

	@Override
	public Optional<Salle> findById(String id) {
		if(this.repo.findById(id).isPresent()) {
			return this.repo.findById(id);
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "la salle avec l'id " + id + " n'existe pas");
		}
	}

	@Override
	public Salle update(Salle f) {
		return this.repo.save(f);
	}

	@Override
	public void delete(String id) {
		this.repo.deleteById(id);
		
	}

}
