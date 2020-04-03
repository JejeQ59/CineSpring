package com.cinema.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cinema.models.Client;
import com.cinema.repositories.ClientRepository;
import com.cinema.services.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository repo;

	@Override
	public Client save(Client c) {
		return this.repo.save(c);
	}

	@Override
	public List<Client> findAll() {
		return this.repo.findAll();
	}

	@Override
	public Optional<Client> findById(String id) {
		if(this.repo.findById(id).isPresent())
		{
			return this.repo.findById(id);
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "le client d'id: " + id + " n'existe pas");
		}
		
	}

	@Override
	public Client update(Client c) {
		return this.repo.save(c);
	}

	@Override
	public void deleteById(String id) {
		 this.repo.deleteById(id);
		
	}
	
	@Override
	public void delete(Client c) {
		 this.repo.delete(c);
		
	}
	
	

}
