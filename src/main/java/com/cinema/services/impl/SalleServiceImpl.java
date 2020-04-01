package com.cinema.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return this.repo.findById(id);
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
