package com.cinema.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cinema.models.Assister;
import com.cinema.models.Seance;
import com.cinema.repositories.AssisterRepository;
import com.cinema.services.AssisterService;

@Service
public class AssisterServiceImpl implements AssisterService {

	@Autowired
	private AssisterRepository repo;

	@Override
	public Assister save(Assister a) {
		return this.repo.save(a);
	}

	@Override
	public List<Assister> findAll() {
		return this.repo.findAll();
	}
	

	@Override
	public Optional<Assister> findById(String id) {
		if(this.repo.findById(id).isPresent()) {
			return this.repo.findById(id);
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "L'objet Assister avec l'id " + id + " n'existe pas");
		}
	}

	@Override
	public Assister update(Assister a) {
		return this.repo.save(a);
	}

	
	@Override
	public void delete(Assister a) {
		this.repo.delete(a);
		
	}

	@Override
	public void deleteById(String id) {
		this.repo.deleteById(id);
		
	}

	@Override
	public Seance addNote(String aId, Integer note) {
		return null;
	}
	

}
