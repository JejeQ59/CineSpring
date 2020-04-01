package com.cinema.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinema.models.Assister;
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
		return this.repo.findById(id);
	}

	@Override
	public Assister update(Assister a) {
		return this.repo.save(a);
	}

	@Override
	public void delete(String id) {
		this.repo.deleteById(id);
		
	}
	

}
